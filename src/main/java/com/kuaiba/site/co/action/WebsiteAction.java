package com.kuaiba.site.co.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.service.CategoryService;
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
	
	@Resource
	private CategoryService cs;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Pagination p, Model model) throws Exception {
		List<Category> cates = cs.findByCollect(new CategoryExample());
		model.addAttribute("cates", cates);
		return "views/home";
	}
	
	@RequestMapping(value = "/{id}/hit", method = RequestMethod.GET)
	public String hit(@PathVariable Long id, Model model) throws Exception {
		return "redirect:" + service.hit(id);
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
