package com.kuaiba.site.front.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.GlobalIds;
import com.kuaiba.site.HttpIds;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.Website;
import com.kuaiba.site.db.entity.WebsiteExample;
import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.front.result.ResultBuilder;
import com.kuaiba.site.service.kit.Pagination;

@Controller
public class WebController extends BaseController implements WebPage {
	
	@RequestMapping(value = HttpIds.ABOUT, method = RequestMethod.GET)
	public String about() {
		return "views/about";
	}

	
	@RequestMapping(value = HttpIds.SEARCH, method = RequestMethod.GET)
	public String search(@RequestParam String q, Pagination p, Model model) throws Exception {
		WebsiteExample example = new WebsiteExample();
		if (StringUtils.isNotEmpty(q)) {
			example.createCriteria().andTitleLike("%" + q + "q");
		}
		PageInfo<Website> pageInfo = websiteService.findByExample(example, p);
		model.addAttribute("p", pageInfo);
		return "views/search";
	}
	
	@RequestMapping(value = HttpIds.LOGIN, method = RequestMethod.GET)
	public String login() {
		boolean flag = (SecurityUtils.getSubject().getSession().getAttribute(GlobalIds.LOGIN_USER) != null);
		if (flag) {
			return "redirect:/";
		}
		return "views/login";
	}
	
	@RequestMapping(value = HttpIds.LOGIN, method = RequestMethod.POST)
	public @ResponseBody Result login(@RequestParam String username, @RequestParam String password) {
		try {
			userService.login(username, password);
			return ResultBuilder.ok();
		} catch (Exception e) { // 登录失败
			return ResultBuilder.failed(e);
		}
	}
	
	@RequestMapping(value = HttpIds.LOGOUT, method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/";
	}
	
	
	@RequestMapping(value = HttpIds.USER_HOME, method = RequestMethod.GET)
	public String dashbord(@PathVariable String name) {
		return "views/users/index";
	}
	
	
	@RequestMapping(value = HttpIds.USER_PROFILE, method = RequestMethod.GET)
	public String profilePage(@PathVariable String name) {
		return "views/users/profile";
	}
	
	
	@RequestMapping(value = HttpIds.USER_PROFILE, method = RequestMethod.POST)
	public String profile(@PathVariable String name, User u) {
		return "views/users/profile";
	}
	
	@RequestMapping(value = HttpIds.GROUPS, method = RequestMethod.GET)
	public String group(Model model) {
		DomainExample oe = new DomainExample();
		oe.createCriteria().andStateEqualTo((byte)1);
		oe.setOrderByClause("weight DESC");
		List<Domain> orgs = domainService.findByCollect(oe);
		model.addAttribute("orgs", orgs);
		return "views/category";
	}
	
	@RequestMapping(value = HttpIds.GROUPS_BY_ID, method = RequestMethod.GET)
	public String groupById(@PathVariable Long id, Model model) {
		CategoryExample example = new CategoryExample();
		example.createCriteria().andIdEqualTo(id);
		List<Category> cates = categoryService.findByCollect(example);
		model.addAttribute("cates", cates);
		return "views/home";
	}
	
	@RequestMapping(value = HttpIds.HOME, method = RequestMethod.GET)
	public String home(Pagination p, Model model) throws Exception {
		List<Category> cates = categoryService.findByCollect(new CategoryExample());
		model.addAttribute("cates", cates);
		return "views/home";
	}
	
	@RequestMapping(value = HttpIds.WEBSITE_HIT, method = RequestMethod.GET)
	public String hit(@PathVariable Long id, Model model) throws Exception {
		return "redirect:" + websiteService.hit(id);
	}
	
	/**
	 * 分享站点
	 * @return
	 */
	@RequestMapping(value = HttpIds.RECOMMEND, method = RequestMethod.GET)
	public String recommend() {
		return "views/recommend";
	}
	
	/**
	 * 分享站点
	 * @return
	 */
	@RequestMapping(value = HttpIds.RECOMMEND, method = RequestMethod.POST)
	public @ResponseBody Result recommend(@RequestParam String url) {
		recommendService.add(url);
		return ResultBuilder.ok();
	}
	
	/**
	 * 添加关注
	 * @param id
	 * @return
	 */
	@RequestMapping(value = HttpIds.WEBSITE_FOLLOW, method = RequestMethod.POST)
	public Result follow(@PathVariable Long id) {
		websiteService.follow(1L, id);
		return ResultBuilder.ok();
	}
	
	/**
	 * 取消关注
	 * @param id
	 * @return
	 */
	@RequestMapping(value = HttpIds.WEBSITE_UNFOLLOW, method = RequestMethod.POST)
	public Result unfollow(@PathVariable Long id) {
		websiteService.unfollow(1L, id);
		return ResultBuilder.ok();
	}

}
