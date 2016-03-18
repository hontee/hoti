package com.kuaiba.site.front.co;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

import org.springframework.web.context.request.WebRequest;

import com.kuaiba.site.core.exception.BaseException;
import com.kuaiba.site.core.exception.ErrorIDs;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.service.ActivityService;
import com.kuaiba.site.service.Auditable;
import com.kuaiba.site.service.BookmarkService;
import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.service.Countable;
import com.kuaiba.site.service.DomainService;
import com.kuaiba.site.service.GroupService;
import com.kuaiba.site.service.MenuService;
import com.kuaiba.site.service.MtypeService;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.service.TrackService;
import com.kuaiba.site.service.UserService;

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
	@Resource
	protected ActivityService activityService;
	@Resource
	protected Auditable auditable;
	@Resource
	protected Countable countable;
	
	protected String redirect(String path) {
		return "redirect:".concat(path);
	}
	
	protected boolean isAjaxRequest(WebRequest webRequest) {
		String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	protected boolean isAjaxUploadRequest(WebRequest webRequest) {
		return webRequest.getParameter("ajaxUpload") != null;
	}
	
	/**
	 * 成功
	 * @return
	 */
	protected SiteResponse ok() {
		Response response = Response.ok().build();
		return new SiteResponse(response.getStatusInfo());
	}
	
	/**
	 * 成功
	 * @param entity
	 * @return
	 */
	protected SiteResponse ok(Object entity) {
		Response response = Response.ok(entity).build();
		return new SiteResponse(response.getEntity());
	}
	
	/**
	 * 失败
	 * @param message
	 * @return
	 */
	protected SiteResponse error(ErrorIDs errorId, String message) {
		SecurityException ex = new SecurityException(errorId, message);
		return new SiteResponse(ex);
	}
	
	/**
	 * 失败
	 * @param e
	 * @return
	 */
	protected SiteResponse error(BaseException e) {
		return new SiteResponse(e);
	}

}
