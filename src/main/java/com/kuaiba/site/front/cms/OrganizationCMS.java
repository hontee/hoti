package com.kuaiba.site.front.cms;

import java.util.ArrayList;
import java.util.List;

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
import com.kuaiba.site.db.entity.Organization;
import com.kuaiba.site.db.entity.OrganizationExample;
import com.kuaiba.site.front.controller.BaseController;
import com.kuaiba.site.front.result.ComboBox;
import com.kuaiba.site.front.result.DataGrid;
import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.front.result.ResultBuilder;
import com.kuaiba.site.front.vo.OrganizationVO;
import com.kuaiba.site.service.kit.Pagination;

@Controller
@RequestMapping(CmsIds.CMS_ORGS)
public class OrganizationCMS extends BaseController {

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/orgs/index";
	}

	@RequiresRoles(value = CmsIds.CREATE)
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addPage() {
		return "cms/orgs/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/orgs/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/orgs/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.DATALIST)
	public @ResponseBody List<ComboBox> datalist() throws Exception {
		OrganizationExample example = new OrganizationExample();
		example.createCriteria().andStateEqualTo((byte)1);
		example.setOrderByClause("weight DESC"); // 按权重排序
		List<Organization> list = organizationService.findByExample(example);
		List<ComboBox> boxes = new ArrayList<>();
		list.forEach((org) -> boxes.add(new ComboBox(org.getId(), org.getTitle())));
		return boxes;
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.LIST)
	public @ResponseBody DataGrid<Organization> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		OrganizationExample example = new OrganizationExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Organization> pageInfo = organizationService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.CREATE, method = RequestMethod.POST)
	public @ResponseBody Result add(OrganizationVO vo) {
		organizationService.add(vo);
		return ResultBuilder.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.DELETE, method = RequestMethod.POST)
	public @ResponseBody Result delete(@PathVariable Long id) {
		organizationService.deleteByPrimaryKey(id);
		return ResultBuilder.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.EDIT, method = RequestMethod.POST)
	public @ResponseBody Result edit(@PathVariable Long id, OrganizationVO vo) {
		organizationService.updateByPrimaryKey(id, vo);
		return ResultBuilder.ok();
	}
	
	private Organization findByPrimaryKey(Long id) {
		return organizationService.findByPrimaryKey(id);
	}
}
