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
import com.kuaiba.site.db.entity.Activity;
import com.kuaiba.site.db.entity.ActivityExample;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.front.controller.BaseController;
import com.kuaiba.site.interceptor.SiteLog;

@Controller
@RequestMapping("/cms/activities")
public class ActivityCMS extends BaseController {
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("tableIds", TableIDs.getList());
		return "cms/activities/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
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
		
		PageInfo<Activity> pageInfo = activityService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@SiteLog(action = "后台删除记录", table = TableIDs.ACTIVITY)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		activityService.deleteByPrimaryKey(id);
		return ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@SiteLog(action = "后台批量删除记录", table = TableIDs.ACTIVITY, clazz = String.class)
	public @ResponseBody SiteResponse delete(@RequestParam String ids, HttpServletRequest request) throws SecurityException {
		activityService.deleteByPrimaryKey(ids.split(","));
		return ok();
	}
	
	private Activity findByPrimaryKey(Long id) throws SecurityException {
		return activityService.findByPrimaryKey(id);
	}

}
