package com.kuaiba.site.core.exceptions;

/**
 * Core exception for all SITE runtime exceptions.
 * @author larry.qi
 *
 */
public class CoreException extends RuntimeException {

	private static final long serialVersionUID = -7601324071173845557L;
	
	private String code;
	
	private String error;

	// 默认：未知错误
	public CoreException() {
		this(ExceptionIds.SYSTEM_DEFAULT);
	}

	public CoreException(ExceptionIds ex) {
		super(ex.name() + " (" + ex.getMessage() + ").");
		this.code = ex.name();
		this.error = ex.getMessage();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
