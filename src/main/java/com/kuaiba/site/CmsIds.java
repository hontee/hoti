package com.kuaiba.site;

/**
 * CMS请求URL
 * @author larry.qi
 */
public interface CmsIds {
	
	// URI
	String CMS_HOME = "/cms";
	String CMS_CATES = "/cms/cates";
	String CMS_MENUS = "/cms/menus";
	String CMS_ORGS = "/cms/orgs";
	String CMS_RECMDS = "/cms/recmds";
	String CMS_USERS = "/cms/users";
	String CMS_WEBSITES = "/cms/websites";
	
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
