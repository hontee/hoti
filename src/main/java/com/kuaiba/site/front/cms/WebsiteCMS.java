package com.kuaiba.site.front.cms;

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
import com.kuaiba.site.CmsIds;
import com.kuaiba.site.db.entity.Website;
import com.kuaiba.site.db.entity.WebsiteExample;
import com.kuaiba.site.front.controller.BaseController;
import com.kuaiba.site.front.result.DataGrid;
import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.front.result.ResultBuilder;
import com.kuaiba.site.front.vo.WebsiteVO;
import com.kuaiba.site.service.kit.Pagination;

@Controller
@RequestMapping(CmsIds.CMS_WEBSITES)
public class WebsiteCMS extends BaseController {

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/websites/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/websites/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/websites/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/websites/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.LIST)
	public @ResponseBody DataGrid<Website> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		WebsiteExample example = new WebsiteExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Website> pageInfo = websiteService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.CREATE, method = RequestMethod.POST)
	public @ResponseBody Result add(WebsiteVO vo) {
		websiteService.add(vo);
		return ResultBuilder.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.DELETE, method = RequestMethod.POST)
	public @ResponseBody Result delete(@PathVariable Long id) {
		websiteService.deleteByPrimaryKey(id);
		return ResultBuilder.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.EDIT, method = RequestMethod.POST)
	public @ResponseBody Result edit(@PathVariable Long id, WebsiteVO vo) {
		websiteService.updateByPrimaryKey(id, vo);
		return ResultBuilder.ok();
	}
	
	private Website findByPrimaryKey(Long id) {
		return websiteService.findByPrimaryKey(id);
	}

}
