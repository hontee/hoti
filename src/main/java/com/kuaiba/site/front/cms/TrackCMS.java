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
import com.kuaiba.site.aop.SiteLog;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.db.entity.TrackExample;
import com.kuaiba.site.front.co.BaseCO;

@Controller
@RequestMapping(CmsIDs.CMS_TRACKS)
public class TrackCMS extends BaseCO {
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/tracks/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/tracks/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.LIST)
	public @ResponseBody DataGrid<Track> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		TrackExample example = new TrackExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andNameLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Track> pageInfo = trackService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.DELETE, method = RequestMethod.POST)
	@SiteLog(action = "后台删除异常", table = TableIDs.TRACK)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		trackService.deleteByPrimaryKey(id);
		return ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.BATCH_DELETE, method = RequestMethod.POST)
	@SiteLog(action = "后台批量删除异常", table = TableIDs.TRACK, clazz = String.class)
	public @ResponseBody SiteResponse delete(@RequestParam String ids, HttpServletRequest request) throws SecurityException {
		trackService.deleteByPrimaryKey(ids.split(","));
		return ok();
	}
	
	private Track findByPrimaryKey(Long id) throws SecurityException {
		return trackService.findByPrimaryKey(id);
	}

}
