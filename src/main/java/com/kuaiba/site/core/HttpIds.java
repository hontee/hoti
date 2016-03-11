package com.kuaiba.site.core;

/**
 * 前端请求URL
 * @author larry.qi
 *
 */
public interface HttpIds {
	
	String SEARCH = "/search";
	String LOGIN = "login";
	String LOGOUT = "logout";
	String USER_HOME = "/{name}/dashbord";
	String USER_PROFILE = "/{name}/profile";
	String CATEGORY = "/cates";
	String CATEGORY_BY_ID = "/cates/{id}";
	String GROUP = "/groups";
	String GROUP_BY_ID = "/groups/{id}";
	String HOME = "";
	String BOOKMARK_HIT = "/bookmarks/{id}/hit";
	String RECOMMEND = "/share";
	String BOOKMARK_FOLLOW = "/bookmarks/{id}/follow";
	String BOOKMARK_UNFOLLOW = "/bookmarks/{id}/unfollow";
	String GROUP_FOLLOW = "/groups/{id}/follow";
	String GROUP_UNFOLLOW = "/groups/{id}/unfollow";
	
}
