package com.kuaiba.site.front.controller;

import javax.annotation.Resource;

import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.service.MenuService;
import com.kuaiba.site.service.OrganizationService;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.service.UserService;
import com.kuaiba.site.service.WebsiteService;

public abstract class BaseController {
	
	@Resource
	protected CategoryService categoryService;
	@Resource
	protected MenuService menuService;
	@Resource
	protected OrganizationService organizationService;
	@Resource
	protected RecommendService recommendService;
	@Resource
	protected UserService userService;
	@Resource
	protected WebsiteService websiteService;

}
