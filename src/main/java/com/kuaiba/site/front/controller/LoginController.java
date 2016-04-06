package com.kuaiba.site.front.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.interceptor.SiteLog;
import com.kuaiba.site.service.UserService;

@Controller
@Scope("prototype")
public class LoginController {
	
	@Resource
	private UserService userService;
	
	/**
	 * @WebPage 用户登录页
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		return AuthzUtil.isAuthorized()? SiteUtil.redirect("/"): "views/login";
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

}
