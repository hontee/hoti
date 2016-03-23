package com.kuaiba.site.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.interceptor.SiteLog;

@Controller
public class SiteController extends BaseController {
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam String q, Pagination p, Model model) throws SecurityException {
		BookmarkExample example = new BookmarkExample();
		if (StringUtils.isNotEmpty(q)) {
			example.createCriteria().andTitleLike("%" + q + "q");
		}
		PageInfo<Bookmark> pageInfo = bookmarkService.findByExample(example, p);
		model.addAttribute("p", pageInfo);
		return "views/search";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		
		if (CurrentUser.isLogin()) {
			return redirect("/");
		}
		return "views/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@SiteLog(action = "用户登录", table = TableIDs.USER, clazz = String.class)
	public @ResponseBody SiteResponse login(
			@RequestParam String username, 
			@RequestParam String password,
			HttpServletRequest request) throws SecurityException {
			userService.login(username, password);
			return ok();
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return redirect("/");
	}
	
	
	@RequestMapping(value = "/{name}/dashbord", method = RequestMethod.GET)
	public String dashbord(@PathVariable String name) {
		return "views/users/index";
	}
	
	
	@RequestMapping(value = "/{name}/profile", method = RequestMethod.GET)
	public String profilePage(@PathVariable String name) {
		return "views/users/profile";
	}
	
	
	@RequestMapping(value = "/{name}/profile", method = RequestMethod.POST)
	@SiteLog(action = "用户更新信息", table = TableIDs.USER)
	public String profile(@PathVariable String name, User u) {
		return "views/users/profile";
	}
	
	@RequestMapping(value = "/cates", method = RequestMethod.GET)
	public String category(Model model) throws SecurityException {
		DomainExample oe = new DomainExample();
		oe.createCriteria().andStateEqualTo((byte)1);
		oe.setOrderByClause("weight DESC");
		List<Domain> orgs = domainService.findByCollect(oe);
		model.addAttribute("orgs", orgs);
		return "views/category";
	}
	
	@RequestMapping(value = "/cates/{id}", method = RequestMethod.GET)
	public String categoryById(@PathVariable Long id, Model model) throws SecurityException {
		CategoryExample example = new CategoryExample();
		example.createCriteria().andIdEqualTo(id);
		List<Category> cates = categoryService.findByCollect(example);
		model.addAttribute("cates", cates);
		return "views/home";
	}
	
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public String group(Model model) throws SecurityException {
		List<Group> list = groupService.findByExample(new GroupExample());
		model.addAttribute("groups", list);
		return "views/group";
	}

	@RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
	public String groupById(@PathVariable Long id, Model model) throws SecurityException {
		Group group = groupService.findByPrimaryKey(id);
		model.addAttribute("record", group);
		return "views/group-bookmark";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Pagination p, Model model) throws SecurityException {
		List<Category> cates = categoryService.findByCollect(new CategoryExample());
		model.addAttribute("cates", cates);
		return "views/home";
	}
	
	@RequestMapping(value = "/bookmarks/{id}/hit", method = RequestMethod.GET)
	public String hit(@PathVariable Long id, Model model) throws SecurityException {
		return redirect(bookmarkService.hit(id));
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
	 * @throws SecurityException 
	 */
	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public @ResponseBody SiteResponse recommend(@RequestParam String url) throws SecurityException {
		recommendService.add(url);
		return ok();
	}
	
	/**
	 * 添加关注
	 * @param id
	 * @return
	 * @throws SecurityException 
	 */
	@RequiresRoles("user")
	@RequestMapping(value = "/bookmarks/{id}/follow", method = RequestMethod.POST)
	public @ResponseBody SiteResponse bookmarkFollow(@PathVariable Long id) throws SecurityException {
		bookmarkService.follow(id);
		return ok();
	}
	
	/**
	 * 取消关注
	 * @param id
	 * @return
	 * @throws SecurityException 
	 */
	@RequiresRoles("user")
	@RequestMapping(value = "/bookmarks/{id}/unfollow", method = RequestMethod.POST)
	public @ResponseBody SiteResponse bookmarkUnfollow(@PathVariable Long id) throws SecurityException {
		bookmarkService.unfollow(id);
		return ok();
	}

	@RequiresRoles("user")
	@RequestMapping(value = "/groups/{id}/follow", method = RequestMethod.POST)
	public @ResponseBody SiteResponse groupFollow(@PathVariable Long id) throws SecurityException {
		groupService.follow(id);
		return ok();
	}

	@RequiresRoles("user")
	@RequestMapping(value = "/groups/{id}/unfollow", method = RequestMethod.POST)
	public @ResponseBody SiteResponse groupUnfollow(@PathVariable Long id) throws SecurityException {
		groupService.unfollow(id);
		return ok();
	}
	
}
