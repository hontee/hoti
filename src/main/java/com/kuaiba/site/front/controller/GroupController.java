package com.kuaiba.site.front.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Filter;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupBookmarkRelation;
import com.kuaiba.site.db.entity.GroupBookmarkRelationExample;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.PagerUtil;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.service.GroupService;

@Controller
@Scope("prototype")
public class GroupController {
	
	@Resource
	private GroupService groupService;
	
	/**
	 * @WebPage 群组 = 我 | 猜你喜欢 | 全部
	 * @param model
	 * @return
	 * @throws SecurityException
	 */
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public String group(
			@RequestParam(required=false) String f,
			Pagination p, 
			Model model, 
			HttpServletRequest request) throws SecurityException {
		
		p.initFrontRows();
		Filter filter = Filter.parse(f);
		f = filter.toString().toLowerCase();
		PageInfo<Group> pageInfo = new PageInfo<>();
		GroupExample example = new GroupExample();
		
		if (filter == Filter.MY) { // 我的站点
			pageInfo = groupService.find(example, p);
		} else if (filter == Filter.LIKE) { // 猜你喜欢
			pageInfo = groupService.find(example, p);
		} else if (filter == Filter.NEW) { // 最新
			example.setOrderByClause("created DESC");
			pageInfo = groupService.find(example, p);
		} else if (filter == Filter.HOT) { // 最热
			example.setOrderByClause("star DESC");
			pageInfo = groupService.find(example, p);
		} else if (filter == Filter.PICK) { // 精选
			example.setOrderByClause("count DESC");
			pageInfo = groupService.find(example, p);
		}
		
		model.addAttribute("f", f);
		model.addAttribute("page", PagerUtil.of(pageInfo, "/groups?f=" + f));
		model.addAttribute("records", pageInfo.getList());
		return "views/group";
	}

	/**
	 * @WebPage 群组下的所有站点（支持分页）
	 * @param id
	 * @param model
	 * @param p
	 * @return
	 * @throws SecurityException
	 */
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
	public String group(
			@PathVariable Long id, 
			@RequestParam(defaultValue="hot") String f,
			Model model, 
			Pagination p,
			HttpServletRequest request) throws SecurityException {
		
		p.initFrontRows();
		Filter filter = Filter.parse(f);
		f = filter.toString().toLowerCase();
		Group record = groupService.findOne(id);
		
		GroupBookmarkRelationExample example = new GroupBookmarkRelationExample();
		example.createCriteria().andGidEqualTo(id);
		
		if (filter == Filter.HOT) {
			p.setOrderBy("hit", "DESC");
		} else if (filter == Filter.NEW) {
			p.setOrderBy("created", "DESC");
		} else if (filter == Filter.PICK) {
			p.setOrderBy("star", "DESC");
		}
		
		PageInfo<GroupBookmarkRelation> pageInfo = groupService.find(example, p);
		record.setBookmarks(pageInfo.getList());
		model.addAttribute("f", f);
		model.addAttribute("page", PagerUtil.of(pageInfo, "/groups/"+id+"/?f=" + f));
		model.addAttribute("record", record);
		return "views/group-bm";
	}

	/**
	 * @WebAPI 关注群组
	 * @param id
	 * @return
	 * @throws SecurityException
	 */
	@RequiresRoles(value = {"user", "admin"})
	@RequestMapping(value = "/groups/{id}/follow", method = RequestMethod.POST)
	public @ResponseBody SiteResponse followGroup(@PathVariable Long id) throws SecurityException {
		groupService.follow(id);
		return SiteUtil.ok();
	}

	/**
	 * @WebAPI 取消关注群组
	 * @param id
	 * @return
	 * @throws SecurityException
	 */
	@RequiresRoles(value = {"user", "admin"})
	@RequestMapping(value = "/groups/{id}/unfollow", method = RequestMethod.POST)
	public @ResponseBody SiteResponse unfollowGroup(@PathVariable Long id) throws SecurityException {
		groupService.unfollow(id);
		return SiteUtil.ok();
	}
	

	
}
