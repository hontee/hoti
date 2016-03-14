package com.kuaiba.site.front.co;

import java.util.List;

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
import com.kuaiba.site.core.GlobalIDs;
import com.kuaiba.site.core.HttpURLs;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.service.utils.AjaxResponse;
import com.kuaiba.site.service.utils.AjaxUtils;
import com.kuaiba.site.service.utils.Pagination;

@Controller
public class SiteCO extends BaseCO implements ISite {
	
	@RequestMapping(value = HttpURLs.SEARCH, method = RequestMethod.GET)
	@Override
	public String search(@RequestParam String q, Pagination p, Model model) throws Exception {
		BookmarkExample example = new BookmarkExample();
		if (StringUtils.isNotEmpty(q)) {
			example.createCriteria().andTitleLike("%" + q + "q");
		}
		PageInfo<Bookmark> pageInfo = bookmarkService.findByExample(example, p);
		model.addAttribute("p", pageInfo);
		return "views/search";
	}
	
	@RequestMapping(value = HttpURLs.LOGIN, method = RequestMethod.GET)
	@Override
	public String login() {
		boolean flag = (SecurityUtils.getSubject().getSession().getAttribute(GlobalIDs.LOGIN_USER) != null);
		if (flag) {
			return redirect("/");
		}
		return "views/login";
	}
	
	@RequestMapping(value = HttpURLs.LOGIN, method = RequestMethod.POST)
	@Override
	public @ResponseBody AjaxResponse login(@RequestParam String username, @RequestParam String password) {
		try {
			userService.login(username, password);
			return AjaxUtils.ok();
		} catch (Exception e) { // 登录失败
			return AjaxUtils.failure(e);
		}
	}
	
	@RequestMapping(value = HttpURLs.LOGOUT, method = RequestMethod.GET)
	@Override
	public String logout() {
		SecurityUtils.getSubject().logout();
		return redirect("/");
	}
	
	
	@RequestMapping(value = HttpURLs.USER_HOME, method = RequestMethod.GET)
	@Override
	public String dashbord(@PathVariable String name) {
		return "views/users/index";
	}
	
	
	@RequestMapping(value = HttpURLs.USER_PROFILE, method = RequestMethod.GET)
	@Override
	public String profilePage(@PathVariable String name) {
		return "views/users/profile";
	}
	
	
	@RequestMapping(value = HttpURLs.USER_PROFILE, method = RequestMethod.POST)
	@Override
	public String profile(@PathVariable String name, User u) {
		return "views/users/profile";
	}
	
	@RequestMapping(value = HttpURLs.CATEGORY, method = RequestMethod.GET)
	@Override
	public String category(Model model) {
		DomainExample oe = new DomainExample();
		oe.createCriteria().andStateEqualTo((byte)1);
		oe.setOrderByClause("weight DESC");
		List<Domain> orgs = domainService.findByCollect(oe);
		model.addAttribute("orgs", orgs);
		return "views/category";
	}
	
	@RequestMapping(value = HttpURLs.CATEGORY_BY_ID, method = RequestMethod.GET)
	@Override
	public String categoryById(@PathVariable Long id, Model model) {
		CategoryExample example = new CategoryExample();
		example.createCriteria().andIdEqualTo(id);
		List<Category> cates = categoryService.findByCollect(example);
		model.addAttribute("cates", cates);
		return "views/home";
	}
	
	@RequestMapping(value = HttpURLs.GROUP, method = RequestMethod.GET)
	@Override
	public String group(Model model) {
		List<Group> list = groupService.findByExample(new GroupExample());
		model.addAttribute("groups", list);
		return "views/group";
	}

	@RequestMapping(value = HttpURLs.GROUP_BY_ID, method = RequestMethod.GET)
	@Override
	public String groupById(@PathVariable Long id, Model model) {
		Group group = groupService.findByPrimaryKey(id);
		model.addAttribute("record", group);
		return "views/group-bookmark";
	}

	@RequestMapping(value = HttpURLs.HOME, method = RequestMethod.GET)
	@Override
	public String home(Pagination p, Model model) throws Exception {
		List<Category> cates = categoryService.findByCollect(new CategoryExample());
		model.addAttribute("cates", cates);
		return "views/home";
	}
	
	@RequestMapping(value = HttpURLs.BOOKMARK_HIT, method = RequestMethod.GET)
	@Override
	public String hit(@PathVariable Long id, Model model) throws Exception {
		return redirect(bookmarkService.hit(id));
	}
	
	/**
	 * 分享站点
	 * @return
	 */
	@RequestMapping(value = HttpURLs.RECOMMEND, method = RequestMethod.GET)
	@Override
	public String recommend() {
		return "views/recommend";
	}
	
	/**
	 * 分享站点
	 * @return
	 */
	@RequestMapping(value = HttpURLs.RECOMMEND, method = RequestMethod.POST)
	@Override
	public @ResponseBody AjaxResponse recommend(@RequestParam String url) {
		recommendService.add(url);
		return AjaxUtils.ok();
	}
	
	/**
	 * 添加关注
	 * @param id
	 * @return
	 */
	@RequiresRoles("user")
	@RequestMapping(value = HttpURLs.BOOKMARK_FOLLOW, method = RequestMethod.POST)
	@Override
	public @ResponseBody AjaxResponse bookmarkFollow(@PathVariable Long id) {
		bookmarkService.follow(id);
		return AjaxUtils.ok();
	}
	
	/**
	 * 取消关注
	 * @param id
	 * @return
	 */
	@RequiresRoles("user")
	@RequestMapping(value = HttpURLs.BOOKMARK_UNFOLLOW, method = RequestMethod.POST)
	@Override
	public @ResponseBody AjaxResponse bookmarkUnfollow(@PathVariable Long id) {
		bookmarkService.unfollow(id);
		return AjaxUtils.ok();
	}

	@RequiresRoles("user")
	@RequestMapping(value = HttpURLs.GROUP_FOLLOW, method = RequestMethod.POST)
	@Override
	public @ResponseBody AjaxResponse groupFollow(@PathVariable Long id) {
		groupService.follow(id);
		return AjaxUtils.ok();
	}

	@RequiresRoles("user")
	@RequestMapping(value = HttpURLs.GROUP_UNFOLLOW, method = RequestMethod.POST)
	@Override
	public @ResponseBody AjaxResponse groupUnfollow(@PathVariable Long id) {
		groupService.unfollow(id);
		return AjaxUtils.ok();
	}
	
}
