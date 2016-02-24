package com.kuaiba.site.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.entity.Website;
import com.kuaiba.site.db.entity.WebsiteExample;
import com.kuaiba.site.service.WebsiteService;
import com.kuaiba.site.support.Pagination;

@Controller()
public class SearchAction {
	
	@Resource
	private WebsiteService service;
	
	/**
	 * 搜索站点
	 * @param q
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam String q, Pagination p, Model model) throws Exception {
		WebsiteExample example = new WebsiteExample();
		if (StringUtils.isNotEmpty(q)) {
			example.createCriteria().andTitleLike("%" + q + "q");
		}
		PageInfo<Website> pageInfo = service.findByExample(example, p);
		model.addAttribute("p", pageInfo);
		return "views/search";
	}

}
