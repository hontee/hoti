package com.kuaiba.site.front.cms;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.CmsIds;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.front.controller.BaseController;
import com.kuaiba.site.front.result.DataGrid;
import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.front.result.ResultBuilder;
import com.kuaiba.site.front.vo.UserVO;
import com.kuaiba.site.service.kit.Pagination;

@Controller
@RequestMapping(CmsIds.CMS_USERS)
public class UserCMS extends BaseController {

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/users/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/users/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/users/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/users/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.LIST)
	public @ResponseBody DataGrid<User> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		UserExample example = new UserExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<User> pageInfo = userService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.CREATE, method = RequestMethod.POST)
	public @ResponseBody Result add(UserVO vo) {
		userService.add(vo);
		return ResultBuilder.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.DELETE, method = RequestMethod.POST)
	public @ResponseBody Result delete(@PathVariable Long id) {
		userService.deleteByPrimaryKey(id);
		return ResultBuilder.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.EDIT, method = RequestMethod.POST)
	public @ResponseBody Result edit(@PathVariable Long id, UserVO vo) {
		userService.updateByPrimaryKey(id, vo);
		return ResultBuilder.ok();
	}
	
	private User findByPrimaryKey(Long id) {
		return userService.findByPrimaryKey(id);
	}

}
