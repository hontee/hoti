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
import com.kuaiba.site.core.AjaxUtils;
import com.kuaiba.site.core.CmsURLs;
import com.kuaiba.site.core.TableIDs;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.db.entity.ResponseEntity;
import com.kuaiba.site.front.co.BaseCO;
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.front.vo.RecommendVO;

@Controller
@RequestMapping(CmsURLs.CMS_RECMDS)
public class RecommendCMS extends BaseCO {
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/recmds/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/recmds/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/edit";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.AUDIT_OK, method = RequestMethod.GET)
	public String auditOKPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/audit-ok";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.AUDIT_NOT, method = RequestMethod.GET)
	public String auditNotPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/audit-not";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.LIST)
	public @ResponseBody DataGrid<Recommend> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		RecommendExample example = new RecommendExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Recommend> pageInfo = recommendService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.POST)
	@Log(action = "后台添加推荐", table = TableIDs.RECOMMEND, clazz = String.class)
	public @ResponseBody ResponseEntity add(@RequestParam String url, HttpServletRequest request) {
		recommendService.add(url);
		return AjaxUtils.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DELETE, method = RequestMethod.POST)
	@Log(action = "后台删除推荐", table = TableIDs.RECOMMEND)
	public @ResponseBody ResponseEntity delete(@PathVariable Long id, HttpServletRequest request) {
		recommendService.deleteByPrimaryKey(id);
		return AjaxUtils.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.POST)
	@Log(action = "后台编辑推荐", table = TableIDs.RECOMMEND, clazz = Recommend.class)
	public @ResponseBody ResponseEntity edit(@PathVariable Long id, RecommendVO vo, HttpServletRequest request) {
		recommendService.updateByPrimaryKey(id, vo);
		return AjaxUtils.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.AUDIT_OK, method = RequestMethod.POST)
	@Log(action = "后台审核推荐通过", table = TableIDs.BOOKMARK, clazz = BookmarkVO.class)
	public @ResponseBody ResponseEntity auditOk(@PathVariable Long id, BookmarkVO vo, HttpServletRequest request) {
		recommendService.audit(id, vo);
		return AjaxUtils.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.AUDIT_NOT, method = RequestMethod.POST)
	@Log(action = "后台审核推荐不通过", table = TableIDs.RECOMMEND, clazz = String.class)
	public @ResponseBody ResponseEntity auditNot(@PathVariable Long id, @RequestParam String remark, HttpServletRequest request) {
		recommendService.audit(id, remark);
		return AjaxUtils.ok();
	}
	
	private Recommend findByPrimaryKey(Long id) {
		return recommendService.findByPrimaryKey(id);
	}

}
