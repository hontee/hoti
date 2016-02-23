package com.kuaiba.site.cms;

import java.util.ArrayList;
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
import com.kuaiba.site.db.entity.Organization;
import com.kuaiba.site.db.entity.OrganizationExample;
import com.kuaiba.site.service.OrganizationService;
import com.kuaiba.site.support.ComboBox;
import com.kuaiba.site.support.DataGrid;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;

@Controller
@RequestMapping("/cms/orgs")
public class OrganizationController {

	@Resource
	private OrganizationService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "cms/orgs/index";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addPage() {
		return "cms/orgs/new";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("record", findByPrimaryKey(id));
		} catch (Exception e) {
		}
		return "cms/orgs/edit";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("record", findByPrimaryKey(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "cms/orgs/view";
	}
	
	@RequestMapping("/datalist")
	public @ResponseBody List<ComboBox> datalist() throws Exception {
		OrganizationExample example = new OrganizationExample();
		example.createCriteria().andStateEqualTo((byte)1);
		example.setOrderByClause("weight DESC"); // 按权重排序
		List<Organization> list = service.findByExample(example);
		List<ComboBox> boxes = new ArrayList<>();
		list.forEach((org) -> boxes.add(new ComboBox(org.getId(), org.getTitle())));
		return boxes;
	}

	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Organization> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		OrganizationExample example = new OrganizationExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Organization> pageInfo = service.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public @ResponseBody Result add(
			@RequestParam String name, 
			@RequestParam String title, 
			@RequestParam(defaultValue = "无") String description, 
			@RequestParam(defaultValue = "0") Integer weight, 
			@RequestParam(defaultValue = "1") Byte state) {
		Organization record = new Organization();
		record.setCreator("admin");
		record.setDescription(description);
		record.setName(name);
		record.setState(state);
		record.setTitle(title);
		record.setWeight(weight);
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
			@RequestParam(defaultValue = "0") Integer weight, 
			@RequestParam(defaultValue = "1") Byte state) {
		Organization record = new Organization();
		record.setId(id);
		record.setDescription(description);
		record.setName(name);
		record.setState(state);
		record.setTitle(title);
		record.setWeight(weight);
		service.updateByPrimaryKey(record);
		return ResultBuilder.ok();
	}
	
	private Organization findByPrimaryKey(Long id) {
		return service.findByPrimaryKey(id);
	}
}
