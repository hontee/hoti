package com.kuaiba.site.front.co;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.User;

/**
 * 前端页面接口
 * @author larry.qi
 *
 */
public interface ISite {
	
	String search(String q, Pagination p, Model model) throws SecurityException;;
	
	String login() throws SecurityException;
	
	SiteResponse login(String username, String password, HttpServletRequest request) throws SecurityException;
	
	String logout() throws SecurityException;
	
	String dashbord(String name) throws SecurityException;
	
	String profilePage(String name) throws SecurityException;
	
	String profile(String name, User u) throws SecurityException;
	
	String category(Model model) throws SecurityException;
	
	String categoryById(Long id, Model model) throws SecurityException;
	
	String group(Model model) throws SecurityException;
	
	String groupById(Long id, Model model) throws SecurityException;
	
	String home(Pagination p, Model model) throws SecurityException;
	
	String hit(Long id, Model model) throws SecurityException;;
	
	String recommend() throws SecurityException;
	
	SiteResponse recommend(String url) throws SecurityException;
	
	SiteResponse bookmarkFollow(Long id) throws SecurityException;
	
	SiteResponse bookmarkUnfollow(Long id) throws SecurityException;
	
	SiteResponse groupFollow(Long id) throws SecurityException;
	
	SiteResponse groupUnfollow(Long id) throws SecurityException;

}
