package com.kuaiba.site.front.controller;

import javax.ws.rs.core.Response;

import org.springframework.web.context.request.WebRequest;

import com.kuaiba.site.core.exception.BaseException;
import com.kuaiba.site.core.exception.ErrorIDs;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.SiteResponse;

public interface SiteUtil {
	
	/**
	 * 重定向
	 * @param path
	 * @return
	 */
	public static String redirect(String path) {
		return "redirect:".concat(path);
	}
	
	/**
	 * 判断是否为AJAX请求
	 * @param webRequest
	 * @return
	 */
	public static boolean isAjaxRequest(WebRequest webRequest) {
		String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	/**
	 * 判断是否为AJAX上传
	 * @param webRequest
	 * @return
	 */
	public static boolean isAjaxUploadRequest(WebRequest webRequest) {
		return webRequest.getParameter("ajaxUpload") != null;
	}
	
	/**
	 * 成功
	 * @return
	 */
	public static SiteResponse ok() {
		Response response = Response.ok().build();
		return new SiteResponse(response.getStatusInfo());
	}
	
	/**
	 * 成功
	 * @param entity
	 * @return
	 */
	public static SiteResponse ok(Object entity) {
		Response response = Response.ok(entity).build();
		return new SiteResponse(response.getEntity());
	}
	
	/**
	 * 失败
	 * @param message
	 * @return
	 */
	public static SiteResponse error(ErrorIDs errorId, String message) {
		SecurityException ex = new SecurityException(errorId, message);
		return new SiteResponse(ex);
	}
	
	/**
	 * 失败
	 * @param e
	 * @return
	 */
	public static SiteResponse error(BaseException e) {
		return new SiteResponse(e);
	}

}
