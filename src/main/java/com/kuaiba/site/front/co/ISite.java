package com.kuaiba.site.front.co;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.Response;
import com.kuaiba.site.db.entity.User;

/**
 * 前端页面接口
 * @author larry.qi
 *
 */
public interface ISite {
	
	String search(String q, Pagination p, Model model) throws Exception;
	
	String login();
	
	Response login(String username, String password, HttpServletRequest request);
	
	String logout();
	
	String dashbord(String name);
	
	String profilePage(String name);
	
	String profile(String name, User u);
	
	String category(Model model);
	
	String categoryById(Long id, Model model);
	
	String group(Model model);
	
	String groupById(Long id, Model model);
	
	String home(Pagination p, Model model) throws Exception;
	
	String hit(Long id, Model model) throws Exception;
	
	String recommend();
	
	Response recommend(String url);
	
	Response bookmarkFollow(Long id);
	
	Response bookmarkUnfollow(Long id);
	
	Response groupFollow(Long id);
	
	Response groupUnfollow(Long id);

}
