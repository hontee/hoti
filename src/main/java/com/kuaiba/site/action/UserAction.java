package com.kuaiba.site.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kuaiba.site.db.entity.User;

/**
 * 用户相关
 * @author larry.qi
 *
 */
@Controller("/u")
public class UserAction {

	/**
	 * 用户个人中心
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/{name}/dashbord", method = RequestMethod.GET)
	public String dashbord(@PathVariable String name) {
		
		return "views/users/index";
	}
	
	/**
	 * 用户设置页面
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/{name}/profile", method = RequestMethod.GET)
	public String profilePage(@PathVariable String name) {
		return "views/users/profile";
	}
	
	/**
	 * 用户设置
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/{name}/profile", method = RequestMethod.POST)
	public String profile(@PathVariable String name, User u) {
		return "views/users/profile";
	}
	
}
