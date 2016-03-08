package com.kuaiba.site.front.co;

import javax.annotation.Resource;

import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.service.MenuService;
import com.kuaiba.site.service.MtypeService;
import com.kuaiba.site.service.DomainService;
import com.kuaiba.site.service.GroupService;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.service.TrackService;
import com.kuaiba.site.service.UserService;
import com.kuaiba.site.service.BookmarkService;

public abstract class BaseCO {
	
	@Resource
	protected CategoryService categoryService;
	@Resource
	protected MenuService menuService;
	@Resource
	protected DomainService domainService;
	@Resource
	protected RecommendService recommendService;
	@Resource
	protected UserService userService;
	@Resource
	protected BookmarkService bookmarkService;
	@Resource
	protected GroupService groupService;
	@Resource
	protected MtypeService mtypeService;
	@Resource
	protected TrackService trackService;
	
	protected String redirect(String path) {
		return "redirect:".concat(path);
	}

}
