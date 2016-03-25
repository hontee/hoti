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
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.StateAuditUtil;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.front.controller.SiteUtil;
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.front.vo.RecommendVO;
import com.kuaiba.site.interceptor.SiteLog;
import com.kuaiba.site.service.Auditable;
import com.kuaiba.site.service.RecommendService;

@Controller
@RequestMapping("/cms/recmds")
public class RecommendCMS {
	
	@Resource
	private RecommendService recommendService;
	@Resource
	private Auditable auditable;
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "cms/recmds/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addPage() {
		return "cms/recmds/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", recommendService.read(id));
		return "cms/recmds/edit";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/ok", method = RequestMethod.GET)
	public String auditOKPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", recommendService.read(id));
		return "cms/recmds/ok";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/refuse", method = RequestMethod.GET)
	public String auditRefusePage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", recommendService.read(id));
		return "cms/recmds/refuse";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", recommendService.read(id));
		return "cms/recmds/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Recommend> dataGrid(
			@RequestParam(required = false) String title, 
			@RequestParam(required = false) Byte state, 
			Pagination p) throws Exception {
		
		RecommendExample example = new RecommendExample();
		RecommendExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(title)) {
			criteria.andTitleLike("%" + title + "%"); // 模糊查询
		}
		
		if (StateAuditUtil.validate(state)) {
			criteria.andStateEqualTo(state);
		}
		
		PageInfo<Recommend> pageInfo = recommendService.search(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@SiteLog(action = "后台添加推荐", table = TableIDs.RECOMMEND, clazz = String.class)
	public @ResponseBody SiteResponse add(@RequestParam String url, HttpServletRequest request) throws SecurityException {
		recommendService.add(url);
		return SiteUtil.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@SiteLog(action = "后台删除推荐", table = TableIDs.RECOMMEND)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		recommendService.delete(id);
		return SiteUtil.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@SiteLog(action = "后台编辑推荐", table = TableIDs.RECOMMEND, clazz = Recommend.class)
	public @ResponseBody SiteResponse edit(@PathVariable Long id, RecommendVO vo, HttpServletRequest request) throws SecurityException {
		recommendService.update(id, vo);
		return SiteUtil.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/ok", method = RequestMethod.POST)
	@SiteLog(action = "后台审核推荐通过", table = TableIDs.BOOKMARK, clazz = BookmarkVO.class)
	public @ResponseBody SiteResponse auditOk(@PathVariable Long id, BookmarkVO vo, HttpServletRequest request) throws SecurityException {
		auditable.auditRecmds(id, vo);
		return SiteUtil.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/refuse", method = RequestMethod.POST)
	@SiteLog(action = "后台审核推荐拒绝", table = TableIDs.RECOMMEND, clazz = String.class)
	public @ResponseBody SiteResponse auditRefuse(@PathVariable Long id, @RequestParam String remark, HttpServletRequest request) throws SecurityException {
		auditable.auditRecmds(id, remark);
		return SiteUtil.ok();
	}

}
