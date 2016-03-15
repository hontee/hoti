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
import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.db.entity.TrackExample;
import com.kuaiba.site.db.model.ResponseEntity;
import com.kuaiba.site.db.model.AjaxUtils;
import com.kuaiba.site.db.model.DataGrid;
import com.kuaiba.site.db.model.Pagination;
import com.kuaiba.site.front.co.BaseCO;

@Controller
@RequestMapping(CmsURLs.CMS_TRACKS)
public class TrackCMS extends BaseCO {
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/tracks/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/tracks/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.LIST)
	public @ResponseBody DataGrid<Track> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		TrackExample example = new TrackExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andExceptionLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Track> pageInfo = trackService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DELETE, method = RequestMethod.POST)
	@Log(action = "后台删除异常追踪", table = TableIDs.TRACK)
	public @ResponseBody ResponseEntity delete(@PathVariable Long id, HttpServletRequest request) {
		trackService.deleteByPrimaryKey(id);
		return AjaxUtils.ok();
	}
	
	private Track findByPrimaryKey(Long id) {
		return trackService.findByPrimaryKey(id);
	}

}
