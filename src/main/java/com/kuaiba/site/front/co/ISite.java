package com.kuaiba.site.front.co;

import org.springframework.ui.Model;

import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.service.utils.AjaxResponse;
import com.kuaiba.site.service.utils.Pagination;

/**
 * 前端页面接口
 * @author larry.qi
 *
 */
public interface ISite {
	
	String search(String q, Pagination p, Model model) throws Exception;
	
	String login();
	
	AjaxResponse login(String username, String password);
	
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
	
	AjaxResponse recommend(String url);
	
	AjaxResponse bookmarkFollow(Long id);
	
	AjaxResponse bookmarkUnfollow(Long id);
	
	AjaxResponse groupFollow(Long id);
	
	AjaxResponse groupUnfollow(Long id);

}
