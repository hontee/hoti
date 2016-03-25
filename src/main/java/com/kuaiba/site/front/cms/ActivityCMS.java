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
import com.kuaiba.site.db.entity.Activity;
import com.kuaiba.site.db.entity.ActivityExample;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.front.controller.SiteUtil;
import com.kuaiba.site.interceptor.SiteLog;
import com.kuaiba.site.service.ActivityService;

@Controller
@RequestMapping("/cms/activities")
public class ActivityCMS {
	
	@Resource
	private ActivityService activityService;
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("tableIds", TableIDs.getList());
		return "cms/activities/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", activityService.read(id));
		return "cms/activities/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Activity> dataGrid(
			@RequestParam(required = false) String name, 
			@RequestParam(required = false) String tbl, 
			Pagination p) throws Exception {
		
		ActivityExample example = new ActivityExample();
		ActivityExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(name)) {
			criteria.andNameLike("%" + name + "%"); // 模糊查询
		}
		
		if (TableIDs.getList().contains(tbl)) {
			criteria.andTblEqualTo(tbl);
		}
		
		PageInfo<Activity> pageInfo = activityService.search(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@SiteLog(action = "后台删除记录", table = TableIDs.ACTIVITY)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		activityService.delete(id);
		return SiteUtil.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@SiteLog(action = "后台批量删除记录", table = TableIDs.ACTIVITY, clazz = String.class)
	public @ResponseBody SiteResponse delete(@RequestParam String ids, HttpServletRequest request) throws SecurityException {
		activityService.delete(ids.split(","));
		return SiteUtil.ok();
	}
	
}
