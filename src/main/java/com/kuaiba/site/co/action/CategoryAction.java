package com.kuaiba.site.co.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class CategoryAction {
	
	@Resource
	private CategoryService service;
	
	@Resource
	private OrganizationService orgService;

	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public String group(Model model) {
		OrganizationExample oe = new OrganizationExample();
		oe.createCriteria().andStateEqualTo((byte)1);
		oe.setOrderByClause("weight DESC");
		List<Organization> orgs = orgService.findByExample(oe);
		
		CategoryExample example = new CategoryExample();
		example.createCriteria().andStateEqualTo((byte)1);
		List<Category> cates = service.findByExample(example);
		model.addAttribute("orgs", orgs);
		model.addAttribute("cates", cates);
		return "views/category";
	}
}
