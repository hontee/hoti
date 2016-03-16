package com.kuaiba.site.front.cms;

import java.util.List;

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
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.Response;
import com.kuaiba.site.front.co.BaseCO;
import com.kuaiba.site.front.vo.CategoryVO;

@Controller
@RequestMapping(CmsURLs.CMS_CATES)
public class CategoryCMS extends BaseCO {
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/cates/index";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/cates/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/cates/edit";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/cates/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.LIST)
	public @ResponseBody DataGrid<Category> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		CategoryExample example = new CategoryExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Category> pageInfo = categoryService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DATALIST)
	public @ResponseBody List<Category> datalist() throws Exception {
		CategoryExample example = new CategoryExample();
		example.createCriteria().andStateEqualTo((byte)1);
		return categoryService.findByExample(example);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.POST)
	@Log(action = "后台添加分类", table = TableIDs.CATEGORY, clazz = CategoryVO.class)
	public @ResponseBody Response add(CategoryVO vo, HttpServletRequest request) {
		categoryService.add(vo);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DELETE, method = RequestMethod.POST)
	@Log(action = "后台删除分类", table = TableIDs.CATEGORY)
	public @ResponseBody Response delete(@PathVariable Long id, HttpServletRequest request) {
		categoryService.deleteByPrimaryKey(id);
		return ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.POST)
	@Log(action = "后台编辑分类", table = TableIDs.CATEGORY, clazz = CategoryVO.class)
	public @ResponseBody Response edit(@PathVariable Long id, CategoryVO vo, HttpServletRequest request) {
		categoryService.updateByPrimaryKey(id, vo);
		return ok();
	}
	
	private Category findByPrimaryKey(Long id) {
		return categoryService.findByPrimaryKey(id);
	}
}
