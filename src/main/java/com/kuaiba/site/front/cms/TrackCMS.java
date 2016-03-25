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
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.db.entity.TrackExample;
import com.kuaiba.site.front.controller.SiteUtil;
import com.kuaiba.site.interceptor.SiteLog;
import com.kuaiba.site.service.TrackService;

@Controller
@RequestMapping("/cms/tracks")
public class TrackCMS {
	
	@Resource
	private TrackService trackService;
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "cms/tracks/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", trackService.read(id));
		return "cms/tracks/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Track> dataGrid(
			@RequestParam(required = false) String name, 
			Pagination p) throws Exception {
		
		TrackExample example = new TrackExample();
		TrackExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(name)) {
			criteria.andNameLike("%" + name + "%"); // 模糊查询
		}
		PageInfo<Track> pageInfo = trackService.search(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@SiteLog(action = "后台删除异常", table = TableIDs.TRACK)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		trackService.delete(id);
		return SiteUtil.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@SiteLog(action = "后台批量删除异常", table = TableIDs.TRACK, clazz = String.class)
	public @ResponseBody SiteResponse delete(@RequestParam String ids, HttpServletRequest request) throws SecurityException {
		trackService.delete(ids.split(","));
		return SiteUtil.ok();
	}
	
}
