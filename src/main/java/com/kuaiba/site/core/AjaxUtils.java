package com.kuaiba.site.core;

import javax.ws.rs.core.Response;

import org.springframework.web.context.request.WebRequest;

import com.kuaiba.site.core.exceptions.AccountException;
import com.kuaiba.site.core.exceptions.CheckedException;
import com.kuaiba.site.core.exceptions.CoreException;
import com.kuaiba.site.core.exceptions.ExceptionIds;
import com.kuaiba.site.core.exceptions.LogicException;
import com.kuaiba.site.db.entity.ResponseEntity;

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
	public static ResponseEntity ok() {
		Response response = Response.ok().build();
		return new ResponseEntity(response.getStatusInfo());
	}
	
	/**
	 * 成功
	 * @param entity
	 * @return
	 */
	public static ResponseEntity ok(Object entity) {
		Response response = Response.ok(entity).build();
		return new ResponseEntity(response.getEntity());
	}
	
	/**
	 * 失败
	 * @param message
	 * @return
	 */
	public static ResponseEntity failure(String message) {
		return new ResponseEntity("LOGIC_CUSTOM", message);
	}
	
	/**
	 * 失败
	 * @param message
	 * @return
	 */
	public static ResponseEntity failure(ExceptionIds ex) {
		return new ResponseEntity(ex.name(), ex.getMessage());
	}
	
	/**
	 * 失败
	 * @param e
	 * @return
	 */
	public static ResponseEntity failure(Throwable t) {
		CoreException e = new CoreException();
		
		if (t instanceof AccountException) {
			e = (AccountException) t;
		} else if (t instanceof CheckedException) {
			e = (CheckedException) t;
		} else if (t instanceof LogicException) {
			e = (LogicException) t;
		}
		
		return new ResponseEntity(e.getCode(), e.getError());
	}

}
