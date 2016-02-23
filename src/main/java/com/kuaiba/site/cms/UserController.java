package com.kuaiba.site.cms;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.security.EncryptUtils;
import com.kuaiba.site.service.UserService;
import com.kuaiba.site.support.DataGrid;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;

@Controller
@RequestMapping("/cms/users")
public class UserController {
	
	@Resource
	private UserService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "cms/users/index";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addPage() {
		return "cms/users/new";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/users/edit";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/users/view";
	}
	
	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<User> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		UserExample example = new UserExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<User> pageInfo = service.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public @ResponseBody Result add(
			@RequestParam String name, 
			@RequestParam String email, 
			@RequestParam String password, 
			@RequestParam(defaultValue = "1") Byte userType, 
			@RequestParam(defaultValue = "1") Byte state) {
		User record = new User();
		record.setEmail(email);
		record.setIsEmailSet((byte)0);
		record.setSalt(EncryptUtils.getRandomSalt()); // 随机盐值
		record.setPassword(EncryptUtils.encrypt(password, record.getSalt())); // 密码加密
		record.setUserType(userType);
		record.setName(name);
		record.setState(state);
		record.setTitle(name);
		service.add(record);
		return ResultBuilder.ok();
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(@PathVariable Long id) {
		service.deleteByPrimaryKey(id);
		return ResultBuilder.ok();
	}


	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public @ResponseBody Result edit(@PathVariable Long id, 
			@RequestParam String name, 
			@RequestParam String title, 
			String description, 
			@RequestParam String email, 
			@RequestParam(defaultValue = "1") Byte userType, 
			@RequestParam(defaultValue = "1") Byte state ) {
		try {
			User record = new User();
			record.setId(id);
			record.setEmail(email);
			record.setUserType(userType);
			record.setName(name);
			record.setState(state);
			record.setTitle(title);
			record.setDescription(description);
			service.updateByPrimaryKey(record);
			return ResultBuilder.ok();
		} catch (Exception e) {
			return ResultBuilder.failed(e);
		}
	}
	
	private User findByPrimaryKey(Long id) {
		return service.findByPrimaryKey(id);
	}

}
