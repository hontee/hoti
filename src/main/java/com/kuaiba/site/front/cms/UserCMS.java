package com.kuaiba.site.front.cms;

import javax.servlet.http.HttpServletRequest;

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
import com.kuaiba.site.aop.Log;
import com.kuaiba.site.core.CmsURLs;
import com.kuaiba.site.core.TableIDs;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.db.model.ResponseEntity;
import com.kuaiba.site.db.model.AjaxUtils;
import com.kuaiba.site.db.model.DataGrid;
import com.kuaiba.site.db.model.Pagination;
import com.kuaiba.site.front.co.BaseCO;
import com.kuaiba.site.front.vo.UserVO;

@Controller
@RequestMapping(CmsURLs.CMS_USERS)
public class UserCMS extends BaseCO {

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/users/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/users/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/users/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/users/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.LIST)
	public @ResponseBody DataGrid<User> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		UserExample example = new UserExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<User> pageInfo = userService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.POST)
	@Log(action = "后台添加用户", table = TableIDs.USER, clazz = UserVO.class)
	public @ResponseBody ResponseEntity add(UserVO vo, HttpServletRequest request) {
		userService.add(vo);
		return AjaxUtils.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DELETE, method = RequestMethod.POST)
	@Log(action = "后台删除用户", table = TableIDs.USER)
	public @ResponseBody ResponseEntity delete(@PathVariable Long id, HttpServletRequest request) {
		userService.deleteByPrimaryKey(id);
		return AjaxUtils.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.POST)
	@Log(action = "后台编辑用户", table = TableIDs.USER, clazz = UserVO.class)
	public @ResponseBody ResponseEntity edit(@PathVariable Long id, UserVO vo, HttpServletRequest request) {
		userService.updateByPrimaryKey(id, vo);
		return AjaxUtils.ok();
	}
	
	private User findByPrimaryKey(Long id) {
		return userService.findByPrimaryKey(id);
	}

}
