package com.kuaiba.site.cms;

import java.util.UUID;

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
import com.kuaiba.site.db.entity.Website;
import com.kuaiba.site.db.entity.WebsiteExample;
import com.kuaiba.site.service.WebsiteService;
import com.kuaiba.site.support.DataGrid;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;

@Controller
@RequestMapping("/cms/websites")
public class WebsiteController {

	@Resource
	private WebsiteService service;

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "cms/websites/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addPage() {
		return "cms/websites/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/websites/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("record", findByPrimaryKey(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "cms/websites/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Website> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		WebsiteExample example = new WebsiteExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Website> pageInfo = service.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public @ResponseBody Result add(
			@RequestParam String url, 
			@RequestParam String title, 
			@RequestParam Long category, 
			@RequestParam(defaultValue = "无") String description, 
			@RequestParam(defaultValue = "1") Byte state) {
		Website record = new Website();
		record.setName(UUID.randomUUID().toString());
		record.setTitle(title);
		record.setUrl(url);
		record.setDescription(description);
		record.setState(state);
		record.setCreateBy(1L);
		record.setCategory(category);
		service.add(record);
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
	public @ResponseBody Result edit(@PathVariable Long id, 
			@RequestParam String url, 
			@RequestParam String title, 
			@RequestParam Long category, 
			@RequestParam String reffer, 
			@RequestParam(defaultValue = "无") String description, 
			@RequestParam(defaultValue = "1") Byte state) {
		Website record = new Website();
		record.setId(id);
		record.setTitle(title);
		record.setUrl(url);
		record.setDescription(description);
		record.setState(state);
		record.setCategory(category);
		service.updateByPrimaryKey(record);
		return ResultBuilder.ok();
	}
	
	private Website findByPrimaryKey(Long id) {
		return service.findByPrimaryKey(id);
	}

}
