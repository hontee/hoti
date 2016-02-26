package com.kuaiba.site.front.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.Organization;
import com.kuaiba.site.db.entity.OrganizationExample;
import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.service.OrganizationService;

/**
 * 分类
 * @author larry.qi
 *
 */
@Controller
public class CategoryController {
	
	@Resource
	private CategoryService service;
	
	@Resource
	private OrganizationService orgService;
	
	@Resource
	private CategoryService cs;

	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public String group(Model model) {
		OrganizationExample oe = new OrganizationExample();
		oe.createCriteria().andStateEqualTo((byte)1);
		oe.setOrderByClause("weight DESC");
		List<Organization> orgs = orgService.findByCollect(oe);
		model.addAttribute("orgs", orgs);
		return "views/category";
	}
	
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
	public String groupById(@PathVariable Long id, Model model) {
		CategoryExample example = new CategoryExample();
		example.createCriteria().andIdEqualTo(id);
		List<Category> cates = cs.findByCollect(example);
		model.addAttribute("cates", cates);
		return "views/home";
	}
	
	

}
