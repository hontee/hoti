package com.kuaiba.site.co.action;

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
import com.kuaiba.site.db.entity.Website;
import com.kuaiba.site.db.entity.WebsiteExample;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.service.WebsiteService;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;

/**
 * 站点
 * @author larry.qi
 *
 */
@Controller
public class WebsiteAction {
	
	@Resource
	private RecommendService recommend;
	
	@Resource
	private WebsiteService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(@RequestParam(required = false) String q, Pagination p, Model model) throws Exception {
		WebsiteExample example = new WebsiteExample();
		if (StringUtils.isNotEmpty(q)) {
			example.createCriteria().andTitleLike("%" + q + "q");
		}
		PageInfo<Website> pageInfo = service.findByExample(example, p);
		model.addAttribute("p", pageInfo);
		model.addAttribute("list", pageInfo.getList());
		return "views/home";
	}
	
	/**
	 * 分享站点
	 * @return
	 */
	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public String recommend() {
		return "views/recommend";
	}
	
	/**
	 * 分享站点
	 * @return
	 */
	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public @ResponseBody Result recommend(@RequestParam String url) {
		recommend.add(url);
		return ResultBuilder.ok();
	}
	
	/**
	 * 添加关注
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/website/{id}/follow", method = RequestMethod.POST)
	public Result follow(@PathVariable Long id) {
		service.follow(1L, id);
		return ResultBuilder.ok();
	}
	
	/**
	 * 取消关注
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/website/{id}/unfollow", method = RequestMethod.POST)
	public Result unfollow(@PathVariable Long id) {
		service.unfollow(1L, id);
		return ResultBuilder.ok();
	}
	
}
