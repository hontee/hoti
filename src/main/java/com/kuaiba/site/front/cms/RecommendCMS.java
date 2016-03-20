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
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.front.controller.BaseController;
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.front.vo.RecommendVO;
import com.kuaiba.site.interceptor.SiteLog;

@Controller
@RequestMapping(CmsIDs.CMS_RECMDS)
public class RecommendCMS extends BaseController {
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/recmds/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/recmds/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/edit";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.AUDIT_OK, method = RequestMethod.GET)
	public String auditOKPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/ok";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.AUDIT_REFUSE, method = RequestMethod.GET)
	public String auditRefusePage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/refuse";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.LIST)
	public @ResponseBody DataGrid<Recommend> dataGrid(
			@RequestParam(required = false) String title, 
			@RequestParam(required = false) Byte state, 
			Pagination p) throws Exception {
		
		RecommendExample example = new RecommendExample();
		RecommendExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(title)) {
			criteria.andTitleLike("%" + title + "%"); // 模糊查询
		}
		
		if (Recommend.checkState(state)) {
			criteria.andStateEqualTo(state);
		}
		
		PageInfo<Recommend> pageInfo = recommendService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.CREATE, method = RequestMethod.POST)
	@SiteLog(action = "后台添加推荐", table = TableIDs.RECOMMEND, clazz = String.class)
	public @ResponseBody SiteResponse add(@RequestParam String url, HttpServletRequest request) throws SecurityException {
		recommendService.add(url);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.DELETE, method = RequestMethod.POST)
	@SiteLog(action = "后台删除推荐", table = TableIDs.RECOMMEND)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		recommendService.deleteByPrimaryKey(id);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.EDIT, method = RequestMethod.POST)
	@SiteLog(action = "后台编辑推荐", table = TableIDs.RECOMMEND, clazz = Recommend.class)
	public @ResponseBody SiteResponse edit(@PathVariable Long id, RecommendVO vo, HttpServletRequest request) throws SecurityException {
		recommendService.updateByPrimaryKey(id, vo);
		return ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.AUDIT_OK, method = RequestMethod.POST)
	@SiteLog(action = "后台审核推荐通过", table = TableIDs.BOOKMARK, clazz = BookmarkVO.class)
	public @ResponseBody SiteResponse auditOk(@PathVariable Long id, BookmarkVO vo, HttpServletRequest request) throws SecurityException {
		auditable.auditRecommend(id, vo);
		return ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.AUDIT_REFUSE, method = RequestMethod.POST)
	@SiteLog(action = "后台审核推荐拒绝", table = TableIDs.RECOMMEND, clazz = String.class)
	public @ResponseBody SiteResponse auditRefuse(@PathVariable Long id, @RequestParam String remark, HttpServletRequest request) throws SecurityException {
		auditable.auditRecommend(id, remark);
		return ok();
	}
	
	private Recommend findByPrimaryKey(Long id) throws SecurityException {
		return recommendService.findByPrimaryKey(id);
	}

}
