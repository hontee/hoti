package com.kuaiba.site.front.co;

import javax.annotation.Resource;

import org.springframework.web.context.request.WebRequest;

import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.service.MenuService;
import com.kuaiba.site.service.MtypeService;
import com.kuaiba.site.service.DomainService;
import com.kuaiba.site.service.GroupService;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.service.TrackService;
import com.kuaiba.site.service.UserService;
import com.kuaiba.site.core.exceptions.AccountException;
import com.kuaiba.site.core.exceptions.CheckedException;
import com.kuaiba.site.core.exceptions.CoreException;
import com.kuaiba.site.core.exceptions.ExceptionIds;
import com.kuaiba.site.core.exceptions.LogicException;
import com.kuaiba.site.db.entity.Response;
import com.kuaiba.site.service.ActivityService;
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
	@Resource
	protected ActivityService activityService;
	
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
	protected Response ok() {
		javax.ws.rs.core.Response response = javax.ws.rs.core.Response.ok().build();
		return new Response(response.getStatusInfo());
	}
	
	/**
	 * 成功
	 * @param entity
	 * @return
	 */
	protected Response ok(Object entity) {
		javax.ws.rs.core.Response response = javax.ws.rs.core.Response.ok(entity).build();
		return new Response(response.getEntity());
	}
	
	/**
	 * 失败
	 * @param message
	 * @return
	 */
	protected Response failure(String message) {
		return new Response("LOGIC_CUSTOM", message);
	}
	
	/**
	 * 失败
	 * @param message
	 * @return
	 */
	protected Response failure(ExceptionIds ex) {
		return new Response(ex.name(), ex.getMessage());
	}
	
	/**
	 * 失败
	 * @param e
	 * @return
	 */
	protected Response failure(Throwable t) {
		CoreException e = new CoreException();
		
		if (t instanceof AccountException) {
			e = (AccountException) t;
		} else if (t instanceof CheckedException) {
			e = (CheckedException) t;
		} else if (t instanceof LogicException) {
			e = (LogicException) t;
		}
		
		return new Response(e.getCode(), e.getError());
	}

}
