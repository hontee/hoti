package com.kuaiba.site.front.controller;

import java.util.List;

import javax.annotation.Resource;
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
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupBookmarkRelation;
import com.kuaiba.site.db.entity.GroupBookmarkRelationExample;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.interceptor.SiteLog;
import com.kuaiba.site.service.BookmarkService;
import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.service.DomainService;
import com.kuaiba.site.service.GroupService;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.service.UserService;

@Controller
public class SiteController {
	
	@Resource
	private BookmarkService bookmarkService;
	@Resource
	private GroupService groupService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private UserService userService;
	@Resource
	private DomainService domainService;
	@Resource
	private RecommendService recommendService;
	
	/**
	 * @WebPage 搜索结果页
	 * @param q
	 * @param p
	 * @param model
	 * @return
	 * @throws SecurityException
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam String q, Pagination p, Model model) 
			throws SecurityException {
		
		BookmarkExample example = new BookmarkExample();
		
		if (StringUtils.isNotEmpty(q)) {
			example.createCriteria().andTitleLike("%" + q + "q");
		}
		
		PageInfo<Bookmark> pageInfo = bookmarkService.find(example, p);
		model.addAttribute("q", q);
		model.addAttribute("records", pageInfo.getList());
		return "views/search";
	}
	
	/**
	 * @WebPage 分类下的所有站点 （支持分页）
	 * @param id
	 * @param model
	 * @return
	 * @throws SecurityException
	 */
	@RequestMapping(value = "/cates/{id}", method = RequestMethod.GET)
	public String cateBm(@PathVariable Long id, Model model, Pagination p) throws SecurityException {
		Category record = categoryService.findOne(id);
		BookmarkExample example = new BookmarkExample();
		example.createCriteria().andCategoryEqualTo(id);
		List<Bookmark> bookmarks = bookmarkService.findAll(example);
		record.setBookmarks(bookmarks);
		model.addAttribute("record", record);
		return "views/cate-bm";
	}
	
	/**
	 * @WebPage 群组
	 * @param model
	 * @return
	 * @throws SecurityException
	 */
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public String group(Model model) throws SecurityException {
		List<Group> list = groupService.findAll(new GroupExample());
		model.addAttribute("records", list);
		return "views/group";
	}

	/**
	 * @WebPage 群组下的所有站点（支持分页）
	 * @param id
	 * @param model
	 * @param p
	 * @return
	 * @throws SecurityException
	 */
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
	public String groupBm(@PathVariable Long id, Model model, Pagination p) throws SecurityException {
		Group record = groupService.findOne(id);
		GroupBookmarkRelationExample example = new GroupBookmarkRelationExample();
		example.createCriteria().andGidEqualTo(id);
		PageInfo<GroupBookmarkRelation> pageInfo = groupService.find(example, p);
		record.setBookmarks(pageInfo.getList());
		model.addAttribute("record", record);
		return "views/group-bm";
	}

	/**
	 * @WebPage 首页
	 * @param p
	 * @param model
	 * @return
	 * @throws SecurityException
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Pagination p, Model model) throws SecurityException {
		List<Bookmark> records = bookmarkService.findAll(new BookmarkExample());
		model.addAttribute("records", records);
		return "views/home";
	}
	
	/**
	 * @WebAPI 更新站点点击率和获取响应URL
	 * @param id
	 * @param model
	 * @return
	 * @throws SecurityException
	 */
	@RequestMapping(value = "/bookmarks/{id}/hit", method = RequestMethod.GET)
	public String bmHit(@PathVariable Long id, Model model) throws SecurityException {
		return SiteUtil.redirect(bookmarkService.updateHit(id));
	}
	
	/**
	 * @WebPage 用户登录页
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		
		if (AuthzUtil.isAuthorized()) { 
			return SiteUtil.redirect("/");
		}
		
		return "views/login";
	}
	
	/**
	 * @WebAPI 用户登录提交
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 * @throws SecurityException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@SiteLog(action = "用户登录", table = TableIDs.USER, clazz = String.class)
	public @ResponseBody SiteResponse login(
			@RequestParam String username, 
			@RequestParam String password,
			HttpServletRequest request) throws SecurityException {
			userService.authenticate(username, password);
			return SiteUtil.ok();
	}
	
	/**
	 * @WebPage 退出登录
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return SiteUtil.redirect("/");
	}
	
	/**
	 * @WebAPI 关注站点
	 * @param id
	 * @return
	 * @throws SecurityException 
	 */
	@RequiresRoles("user")
	@RequestMapping(value = "/bookmarks/{id}/follow", method = RequestMethod.POST)
	public @ResponseBody SiteResponse followBm(@PathVariable Long id) throws SecurityException {
		bookmarkService.follow(id);
		return SiteUtil.ok();
	}
	
	/**
	 * @WebAPI 取消关注站点
	 * @param id
	 * @return
	 * @throws SecurityException 
	 */
	@RequiresRoles("user")
	@RequestMapping(value = "/bookmarks/{id}/unfollow", method = RequestMethod.POST)
	public @ResponseBody SiteResponse unfollowBm(@PathVariable Long id) throws SecurityException {
		bookmarkService.unfollow(id);
		return SiteUtil.ok();
	}

	/**
	 * @WebAPI 关注群组
	 * @param id
	 * @return
	 * @throws SecurityException
	 */
	@RequiresRoles("user")
	@RequestMapping(value = "/groups/{id}/follow", method = RequestMethod.POST)
	public @ResponseBody SiteResponse followGroup(@PathVariable Long id) throws SecurityException {
		groupService.follow(id);
		return SiteUtil.ok();
	}

	/**
	 * @WebAPI 取消关注群组
	 * @param id
	 * @return
	 * @throws SecurityException
	 */
	@RequiresRoles("user")
	@RequestMapping(value = "/groups/{id}/unfollow", method = RequestMethod.POST)
	public @ResponseBody SiteResponse unfollowGroup(@PathVariable Long id) throws SecurityException {
		groupService.unfollow(id);
		return SiteUtil.ok();
	}
	
	/**
	 * @WebPage 分享站点
	 * @return
	 */
	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public String share() {
		return "views/share";
	}
	
	/**
	 * @WebAPI 分享站点
	 * @return
	 * @throws SecurityException 
	 */
	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public @ResponseBody SiteResponse share(@RequestParam String url) throws SecurityException {
		recommendService.add(url);
		return SiteUtil.ok();
	}
	
}
