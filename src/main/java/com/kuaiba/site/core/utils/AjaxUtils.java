package com.kuaiba.site.core.utils;

import javax.ws.rs.core.Response;

import org.springframework.web.context.request.WebRequest;

import com.kuaiba.site.core.exceptions.AccountException;
import com.kuaiba.site.core.exceptions.CheckedException;
import com.kuaiba.site.core.exceptions.CoreException;
import com.kuaiba.site.core.exceptions.ExceptionIds;
import com.kuaiba.site.core.exceptions.LogicException;

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
	public static AjaxResponse failure(String message) {
		return new AjaxResponse("LOGIC_CUSTOM", message);
	}
	
	/**
	 * 失败
	 * @param message
	 * @return
	 */
	public static AjaxResponse failure(ExceptionIds ex) {
		return new AjaxResponse(ex.name(), ex.getMessage());
	}
	
	/**
	 * 失败
	 * @param e
	 * @return
	 */
	public static AjaxResponse failure(Throwable t) {
		CoreException e = new CoreException();
		
		if (t instanceof AccountException) {
			e = (AccountException) t;
		} else if (t instanceof CheckedException) {
			e = (CheckedException) t;
		} else if (t instanceof LogicException) {
			e = (LogicException) t;
		}
		
		return new AjaxResponse(e.getCode(), e.getError());
	}

}
