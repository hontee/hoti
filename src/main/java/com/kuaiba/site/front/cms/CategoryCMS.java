package com.kuaiba.site.front.cms;

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
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.front.controller.BaseController;
import com.kuaiba.site.front.result.DataGrid;
import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.front.result.ResultBuilder;
import com.kuaiba.site.front.vo.CategoryVO;
import com.kuaiba.site.service.kit.Pagination;

@Controller
@RequestMapping(CmsIds.CMS_CATES)
public class CategoryCMS extends BaseController {
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/cates/index";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/cates/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/cates/edit";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/cates/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.LIST)
	public @ResponseBody DataGrid<Category> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		CategoryExample example = new CategoryExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Category> pageInfo = categoryService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.DATALIST)
	public @ResponseBody List<Category> datalist() throws Exception {
		CategoryExample example = new CategoryExample();
		example.createCriteria().andStateEqualTo((byte)1);
		return categoryService.findByExample(example);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.CREATE, method = RequestMethod.POST)
	public @ResponseBody Result add(CategoryVO vo) {
		categoryService.add(vo);
		return ResultBuilder.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.DELETE, method = RequestMethod.POST)
	public @ResponseBody Result delete(@PathVariable Long id) {
		categoryService.deleteByPrimaryKey(id);
		return ResultBuilder.ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIds.EDIT, method = RequestMethod.POST)
	public @ResponseBody Result edit(@PathVariable Long id,  CategoryVO vo) {
		categoryService.updateByPrimaryKey(id, vo);
		return ResultBuilder.ok();
	}
	
	private Category findByPrimaryKey(Long id) {
		return categoryService.findByPrimaryKey(id);
	}
}
