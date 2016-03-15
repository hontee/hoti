package com.kuaiba.site.front.co;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.ResponseEntity;
import com.kuaiba.site.db.entity.User;

/**
 * 前端页面接口
 * @author larry.qi
 *
 */
public interface ISite {
	
	String search(String q, Pagination p, Model model) throws Exception;
	
	String login();
	
	ResponseEntity login(String username, String password, HttpServletRequest request);
	
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
	
	ResponseEntity recommend(String url);
	
	ResponseEntity bookmarkFollow(Long id);
	
	ResponseEntity bookmarkUnfollow(Long id);
	
	ResponseEntity groupFollow(Long id);
	
	ResponseEntity groupUnfollow(Long id);

}
