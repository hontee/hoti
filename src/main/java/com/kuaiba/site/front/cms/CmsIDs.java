package com.kuaiba.site.front.cms;

/**
 * CMS请求URL
 * @author larry.qi
 */
public interface CmsIDs {
	
	// URI
	String CMS_HOME = "/cms";
	String CMS_ACTIVITIES = "/cms/activities";
	String CMS_BOOKMARKS = "/cms/bookmarks";
	String CMS_CATES = "/cms/cates";
	String CMS_DOMAINS = "/cms/domains";
	String CMS_GROUPS = "/cms/groups";
	String CMS_MENUS = "/cms/menus";
	String CMS_MTYPES = "/cms/mtypes";
	String CMS_RECMDS = "/cms/recmds";
	String CMS_TRACKS = "/cms/tracks";
	String CMS_USERS = "/cms/users";
	
	// Commons
	String HOME = "";
	String CREATE = "/new";
	String EDIT = "/{id}/edit";
	String VIEW = "/{id}";
	String LIST = "/list";
	String DATALIST = "/datalist";
	String DELETE = "/{id}/delete";
	String BATCH_DELETE = "/delete";
	String PASSWORD = "/{id}/password";
	String COUNT_TASK = "/count/task";
	String FOLLOW = "/{id}/follow";
	String FOLLOWS = "/{id}/follows";
	String GROUP_BOOKMARK = "/{id}/bookmark";
	String GROUP_BOOKMARKS = "/{id}/bookmarks";
	String GROUP_MANAGER = "/{id}/manager";
	String AUDIT_OK = "/{id}/ok";
	String AUDIT_REFUSE = "/{id}/refuse";
}
