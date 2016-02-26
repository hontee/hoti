package com.kuaiba.site.front.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuaiba.site.Constants;
import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.front.result.ResultBuilder;
import com.kuaiba.site.service.UserService;

/**
 * 用户登录
 * @author larry.qi
 *
 */
@Controller
public class LoginController {
	
	@Resource
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		boolean flag = (SecurityUtils.getSubject().getSession().getAttribute(Constants.LOGIN_USER) != null);
		if (flag) {
			return "redirect:/";
		}
		return "views/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Result login(@RequestParam String username, @RequestParam String password) {
		try {
			service.login(username, password);
			return ResultBuilder.ok();
		} catch (Exception e) { // 登录失败
			return ResultBuilder.failed(e);
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/";
	}

}
