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
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.FollowUser;
import com.kuaiba.site.db.entity.FollowUserExample;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupBookmarkRelation;
import com.kuaiba.site.db.entity.GroupBookmarkRelationExample;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.front.controller.BaseController;
import com.kuaiba.site.front.vo.GroupVO;
import com.kuaiba.site.interceptor.SiteLog;

@Controller
@RequestMapping(CmsIDs.CMS_GROUPS)
public class GroupCMS extends BaseController {

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/groups/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/groups/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/groups/edit";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.FOLLOW, method = RequestMethod.GET)
	public String followPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("id", id);
		return "cms/groups/follow";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.GROUP_BOOKMARK, method = RequestMethod.GET)
	public String bookmarkPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("id", id);
		return "cms/groups/bookmark";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.GROUP_MANAGER, method = RequestMethod.GET)
	public String managerPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("id", id);
		return "cms/groups/manager";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/groups/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.LIST)
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
		
		if (Group.checkState(state)) {
			criteria.andStateEqualTo(state);
		}
		
		if (Group.checkGtype(gtype)) {
			criteria.andGtypeEqualTo(gtype);
		}
		
		PageInfo<Group> pageInfo = groupService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.GROUP_BOOKMARKS)
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
		
		if (Group.checkState(state)) {
			criteria.andStateEqualTo(state);
		}
		
		PageInfo<GroupBookmarkRelation> pageInfo = followable.findGBRelation(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.FOLLOWS)
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
		
		if (User.checkUserType(userType)) {
			criteria.andUserTypeEqualTo(userType);
		}
		
		if (User.checkState(state)) {
			criteria.andStateEqualTo(state);
		}
		
		PageInfo<FollowUser> pageInfo = followable.findGroupUser(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.CREATE, method = RequestMethod.POST)
	@SiteLog(action = "后台添加群组", table = TableIDs.GROUP, clazz = GroupVO.class)
	public @ResponseBody SiteResponse add(GroupVO vo, HttpServletRequest request) throws SecurityException {
		groupService.add(vo);
		return ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.GROUP_BOOKMARK, method = RequestMethod.POST)
	@SiteLog(action = "后台群组批量添加站点", table = TableIDs.GROUP)
	public @ResponseBody SiteResponse addBookmarks(@RequestParam Long[] ids, @PathVariable Long id, HttpServletRequest request) throws SecurityException {
		groupService.addBookmarks(id, ids);
		return ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.GROUP_MANAGER, method = RequestMethod.POST)
	@SiteLog(action = "后台群组批量移除站点", table = TableIDs.GROUP)
	public @ResponseBody SiteResponse removeBookmarks(@RequestParam Long[] ids, @PathVariable Long id, HttpServletRequest request) throws SecurityException {
		groupService.removeBookmark(id, ids);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.DELETE, method = RequestMethod.POST)
	@SiteLog(action = "后台删除群组", table = TableIDs.GROUP)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		groupService.deleteByPrimaryKey(id);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.EDIT, method = RequestMethod.POST)
	@SiteLog(action = "后台编辑群组", table = TableIDs.GROUP, clazz = GroupVO.class)
	public @ResponseBody SiteResponse edit(@PathVariable Long id, GroupVO vo, HttpServletRequest request) throws SecurityException {
		groupService.updateByPrimaryKey(id, vo);
		return ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.COUNT_TASK, method = RequestMethod.POST)
	@SiteLog(action = "后台统计群组数据", table = TableIDs.GROUP, clazz = String.class)
	public @ResponseBody SiteResponse countTask(
			@RequestParam(defaultValue = "后台统计群组数据") String desc, 
			HttpServletRequest request) throws SecurityException {
		countable.countGroupTask();
		return ok();
	}
	
	private Group findByPrimaryKey(Long id) throws SecurityException {
		return groupService.findByPrimaryKey(id);
	}
	
}
