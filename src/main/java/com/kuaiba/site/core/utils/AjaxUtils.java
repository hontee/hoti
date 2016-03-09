package com.kuaiba.site.core.utils;

import javax.ws.rs.core.Response;

import org.springframework.web.context.request.WebRequest;

import com.kuaiba.site.core.exceptions.CoreException;

public class AjaxUtils {

	public static boolean isAjaxRequest(WebRequest webRequest) {
		String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	public static boolean isAjaxUploadRequest(WebRequest webRequest) {
		return webRequest.getParameter("ajaxUpload") != null;
	}
	
	private AjaxUtils() {}
	
	/**
	 * 成功
	 * @return
	 */
	public static AjaxResponse ok() {
		Response response = Response.ok().build();
		return new AjaxResponse(response.getStatusInfo());
	}
	
	/**
	 * 成功
	 * @param entity
	 * @return
	 */
	public static AjaxResponse ok(Object entity) {
		Response response = Response.ok(entity).build();
		return new AjaxResponse(response.getEntity());
	}
	
	/**
	 * 失败
	 * @param message
	 * @return
	 */
	public static AjaxResponse failed(String message) {
		return new AjaxResponse(1001, message);
	}
	
	/**
	 * 失败
	 * @param e
	 * @return
	 */
	public static AjaxResponse failed(Throwable e) {
		String message = "系统错误";
		if (e instanceof CoreException) {
			message = e.getMessage();
		}
		return failed(message);
	}

}
