package com.kuaiba.site.core;

/**
 * CMS请求URL
 * @author larry.qi
 */
public interface CmsIds {
	
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
	String AUDIT_OK = "/{id}/auditOk";
	String AUDIT_NOT = "/{id}/auditNOT";
}
