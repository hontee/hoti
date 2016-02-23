package com.kuaiba.site.cms;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.support.DataGrid;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;

@Controller
@RequestMapping("/cms/cates")
public class CategoryController {
	
	@Resource
	private CategoryService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "cms/cates/index";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addPage() {
		return "cms/cates/new";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("record", findByPrimaryKey(id));
		} catch (Exception e) {
		}
		return "cms/cates/edit";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("record", findByPrimaryKey(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "cms/cates/view";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Category> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		CategoryExample example = new CategoryExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Category> pageInfo = service.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequestMapping("/datalist")
	public @ResponseBody List<Category> datalist() throws Exception {
		CategoryExample example = new CategoryExample();
		example.createCriteria().andStateEqualTo((byte)1);
		return service.findByExample(example);
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public @ResponseBody Result add(
			@RequestParam String name, 
			@RequestParam String title, 
			@RequestParam(defaultValue = "无") String description, 
			@RequestParam Long organization, 
			@RequestParam(defaultValue = "1") Byte state) {
		Category record = new Category();
		record.setName(name);
		record.setTitle(title);
		record.setDescription(description);
		record.setState(state);
		record.setOrganization(organization);
		record.setCreateBy(1L);
		service.add(record);
		return ResultBuilder.ok();
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(@PathVariable Long id) {
		try {
			service.deleteByPrimaryKey(id);
			return ResultBuilder.ok();
		} catch (Exception e) {
			return ResultBuilder.failed(e);
		}
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public @ResponseBody Result edit(@PathVariable Long id, 
			@RequestParam String name, 
			@RequestParam String title, 
			@RequestParam(defaultValue = "无") String description, 
			@RequestParam Long organization, 
			@RequestParam(defaultValue = "1") Byte state) {
		Category record = new Category();
		record.setId(id);
		record.setName(name);
		record.setTitle(title);
		record.setDescription(description);
		record.setState(state);
		record.setOrganization(organization);
		service.updateByPrimaryKey(record);
		return ResultBuilder.ok();
	}
	
	private Category findByPrimaryKey(Long id) {
		return service.findByPrimaryKey(id);
	}
}
