package com.kuaiba.site.front.controller;

import org.springframework.ui.Model;

import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.service.kit.Pagination;

/**
 * 前端页面接口
 * @author larry.qi
 *
 */
public interface WebPage {
	
	String about();
	
	String search(String q, Pagination p, Model model) throws Exception;
	
	String login();
	
	Result login(String username, String password);
	
	String logout();
	
	String dashbord(String name);
	
	String profilePage(String name);
	
	String profile(String name, User u);
	
	String group(Model model);
	
	String groupById(Long id, Model model);
	
	String home(Pagination p, Model model) throws Exception;
	
	String hit(Long id, Model model) throws Exception;
	
	String recommend();
	
	Result recommend(String url);
	
	Result bookmarkFollow(Long id);
	
	Result bookmarkUnfollow(Long id);
	
	Result groupFollow(Long id);
	
	Result groupUnfollow(Long id);

}
