package com.kuaiba.site.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;

/**
 * 用户登录
 * @author larry.qi
 *
 */
@Controller
public class LoginAction {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "views/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result login(@RequestParam String username, @RequestParam String password) {
		return ResultBuilder.ok();
	}

}
