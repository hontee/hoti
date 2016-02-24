package com.kuaiba.site.cms;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuaiba.site.service.UserService;
import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ValidUtils;

@Controller
@RequestMapping("/cms")
public class HomeController {
	
	@Resource
	private UserService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home() {
		return "cms/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "cms/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Result login(String username, String password) {
		try {
			ValidUtils.checkEmail(username);
			username = service.findNameByEmail(username);
		} catch (Exception e) {
			// 非邮箱登录
		}
		
		return service.login(username, password);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "cms/home";
	}

}
