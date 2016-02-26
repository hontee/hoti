package com.kuaiba.site.front.cms;

import javax.annotation.Resource;

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
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.front.result.DataGrid;
import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.front.result.ResultBuilder;
import com.kuaiba.site.front.vo.RecommendVO;
import com.kuaiba.site.front.vo.WebsiteVO;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.service.WebsiteService;
import com.kuaiba.site.service.kit.Pagination;

@Controller
@RequestMapping("/cms/recmds")
public class RecommendCMS {
	
	@Resource
	private RecommendService service;
	@Resource
	private WebsiteService webService;

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
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/edit";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/auditOk", method = RequestMethod.GET)
	public String auditOKPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/audit-ok";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/auditNot", method = RequestMethod.GET)
	public String auditNotPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/audit-not";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/recmds/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Recommend> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		RecommendExample example = new RecommendExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Recommend> pageInfo = service.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public @ResponseBody Result add(@RequestParam String url) {
		service.add(url);
		return ResultBuilder.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(@PathVariable Long id) {
		service.deleteByPrimaryKey(id);
		return ResultBuilder.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public @ResponseBody Result edit(@PathVariable Long id, RecommendVO vo) {
		service.updateByPrimaryKey(id, vo);
		return ResultBuilder.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/auditOk", method = RequestMethod.POST)
	public @ResponseBody Result auditOk(@PathVariable Long id, WebsiteVO vo) {
		service.audit(id, vo);
		return ResultBuilder.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/auditNot", method = RequestMethod.POST)
	public @ResponseBody Result auditNot(@PathVariable Long id, @RequestParam String remark) {
		service.audit(id, remark);
		return ResultBuilder.ok();
	}
	
	private Recommend findByPrimaryKey(Long id) {
		return service.findByPrimaryKey(id);
	}

}
