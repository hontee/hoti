package com.kuaiba.site.front.cms;

import javax.annotation.Resource;
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
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.FollowUser;
import com.kuaiba.site.db.entity.FollowUserExample;
import com.kuaiba.site.db.entity.GTypeUtil;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupBookmarkRelation;
import com.kuaiba.site.db.entity.GroupBookmarkRelationExample;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.StateUtil;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.db.entity.UserTypeUtil;
import com.kuaiba.site.front.controller.SiteUtil;
import com.kuaiba.site.front.vo.GroupVO;
import com.kuaiba.site.interceptor.SiteLog;
import com.kuaiba.site.service.Countable;
import com.kuaiba.site.service.Followable;
import com.kuaiba.site.service.GroupService;

@Controller
@RequestMapping("/cms/groups")
public class GroupCMS {
	
	@Resource
	private GroupService groupService;
	@Resource
	private Followable followable;
	@Resource
	private Countable countable;

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "cms/groups/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addPage() {
		return "cms/groups/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", groupService.read(id));
		return "cms/groups/edit";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/follow", method = RequestMethod.GET)
	public String followPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("id", id);
		return "cms/groups/follow";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/bookmark", method = RequestMethod.GET)
	public String bookmarkPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("id", id);
		return "cms/groups/bookmark";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/manager", method = RequestMethod.GET)
	public String managerPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("id", id);
		return "cms/groups/manager";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", groupService.read(id));
		return "cms/groups/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Group> dataGrid(
			@RequestParam(required = false) String title, 
			@RequestParam(required = false) Long category, 
			@RequestParam(required = false) String gtype, 
			@RequestParam(required = false) Byte state, 
			Pagination p) throws Exception {
		
		GroupExample example = new GroupExample();
		GroupExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(title)) {
			criteria.andTitleLike("%" + title + "%"); // 模糊查询
		}
		
		if (category != null && category > 0) {
			criteria.andCategoryEqualTo(category);
		}
		
		if (StateUtil.validate(state)) {
			criteria.andStateEqualTo(state);
		}
		
		if (GTypeUtil.validate(gtype)) {
			criteria.andGtypeEqualTo(gtype);
		}
		
		PageInfo<Group> pageInfo = groupService.search(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/bookmarks")
	public @ResponseBody DataGrid<GroupBookmarkRelation> groupBookmarks(
			@PathVariable Long id,
			@RequestParam(required = false) String title, 
			@RequestParam(required = false) Long category, 
			@RequestParam(required = false) Byte state, 
			Pagination p) throws Exception {
		
		GroupBookmarkRelationExample example = new GroupBookmarkRelationExample();
		GroupBookmarkRelationExample.Criteria criteria = example.createCriteria();
		
		criteria.andGidEqualTo(id); // GID必须
		
		if (StringUtils.isNotBlank(title)) {
			criteria.andTitleLike("%" + title + "%"); // 模糊查询
		}
		
		if (category != null && category > 0) {
			criteria.andCategoryEqualTo(category);
		}
		
		if (StateUtil.validate(state)) {
			criteria.andStateEqualTo(state);
		}
		
		PageInfo<GroupBookmarkRelation> pageInfo = followable.findGBRelation(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/follows")
	public @ResponseBody DataGrid<FollowUser> followUsers(
			@PathVariable Long id, 
			@RequestParam(required = false) String name, 
			@RequestParam(required = false) Byte userType, 
			@RequestParam(required = false) Byte state, 
			Pagination p) throws Exception {
		
		FollowUserExample example = new FollowUserExample();
		FollowUserExample.Criteria criteria = example.createCriteria();
		
		criteria.andFidEqualTo(id); // FID必须
		
		if (StringUtils.isNotBlank(name)) {
			criteria.andNameLike("%" + name + "%"); // 模糊查询
		}
		
		if (UserTypeUtil.validate(userType)) {
			criteria.andUserTypeEqualTo(userType);
		}
		
		if (StateUtil.validate(state)) {
			criteria.andStateEqualTo(state);
		}
		
		PageInfo<FollowUser> pageInfo = followable.findGroupUser(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@SiteLog(action = "后台添加群组", table = TableIDs.GROUP, clazz = GroupVO.class)
	public @ResponseBody SiteResponse add(GroupVO vo, HttpServletRequest request) throws SecurityException {
		groupService.add(vo);
		return SiteUtil.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/bookmark", method = RequestMethod.POST)
	@SiteLog(action = "后台群组批量添加站点", table = TableIDs.GROUP)
	public @ResponseBody SiteResponse addBookmark(@RequestParam Long[] ids, @PathVariable Long id, HttpServletRequest request) throws SecurityException {
		groupService.addBookmark(id, ids);
		return SiteUtil.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/manager", method = RequestMethod.POST)
	@SiteLog(action = "后台群组批量移除站点", table = TableIDs.GROUP)
	public @ResponseBody SiteResponse removeBookmarks(@RequestParam Long[] ids, @PathVariable Long id, HttpServletRequest request) throws SecurityException {
		groupService.removeBookmark(id, ids);
		return SiteUtil.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@SiteLog(action = "后台删除群组", table = TableIDs.GROUP)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		groupService.delete(id);
		return SiteUtil.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@SiteLog(action = "后台编辑群组", table = TableIDs.GROUP, clazz = GroupVO.class)
	public @ResponseBody SiteResponse edit(@PathVariable Long id, GroupVO vo, HttpServletRequest request) throws SecurityException {
		groupService.update(id, vo);
		return SiteUtil.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/count/task", method = RequestMethod.POST)
	@SiteLog(action = "后台统计群组数据", table = TableIDs.GROUP, clazz = String.class)
	public @ResponseBody SiteResponse countTask(
			@RequestParam(defaultValue = "后台统计群组数据") String desc, 
			HttpServletRequest request) throws SecurityException {
		countable.countGroupTask();
		return SiteUtil.ok();
	}
	
}
