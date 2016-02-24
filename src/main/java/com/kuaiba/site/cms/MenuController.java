package com.kuaiba.site.cms;

import java.util.List;

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
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.service.MenuService;
import com.kuaiba.site.support.DataGrid;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;

@Controller
@RequestMapping("/cms/menus")
public class MenuController {
	
	@Resource
	private MenuService service;

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "cms/menus/index";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addPage() {
		return "cms/menus/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("record", findByPrimaryKey(id));
		} catch (Exception e) {
		}
		return "cms/menus/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("record", findByPrimaryKey(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "cms/menus/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping("/datalist")
	public @ResponseBody List<Menu> datalist() throws Exception {
		MenuExample example = new MenuExample();
		example.createCriteria().andStateEqualTo((byte)1);
		example.setOrderByClause("weight DESC"); // 按权重排序
		return service.findByExample(example);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Menu> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		MenuExample example = new MenuExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Menu> pageInfo = service.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public @ResponseBody Result add(
			@RequestParam String name, 
			@RequestParam String title, 
			@RequestParam String path,
			@RequestParam String organization, 
			@RequestParam(defaultValue = "无") String description, 
			@RequestParam(defaultValue = "0") Integer weight, 
			@RequestParam(defaultValue = "1") Byte state) {
		Menu record = new Menu();
		record.setCreator("admin");
		record.setDescription(description);
		record.setName(name);
		record.setState(state);
		record.setTitle(title);
		record.setPath(path);
		record.setWeight(weight);
		record.setOrganization(organization);
		service.add(record);
		return ResultBuilder.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(@PathVariable Long id) {
		try {
			service.deleteByPrimaryKey(id);
			return ResultBuilder.ok();
		} catch (Exception e) {
			return ResultBuilder.failed(e);
		}
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public @ResponseBody Result edit(@PathVariable Long id, 
			@RequestParam String name, 
			@RequestParam String title, 
			@RequestParam String path,
			@RequestParam String organization,
			@RequestParam(defaultValue = "无") String description, 
			@RequestParam(defaultValue = "0") Integer weight, 
			@RequestParam(defaultValue = "1") Byte state) {
		Menu record = new Menu();
		record.setId(id);
		record.setDescription(description);
		record.setName(name);
		record.setState(state);
		record.setTitle(title);
		record.setPath(path);
		record.setWeight(weight);
		record.setOrganization(organization);
		try {
			service.updateByPrimaryKey(record);
			return ResultBuilder.ok();
		} catch (Exception e) {
			return ResultBuilder.failed(e);
		}
	}
	
	private Menu findByPrimaryKey(Long id) {
		return service.findByPrimaryKey(id);
	}

}
