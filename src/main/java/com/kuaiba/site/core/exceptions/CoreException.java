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

	public CoreException() {
		super();
	}

	public CoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public CoreException(String message) {
		super(message);
	}
	
	public CoreException(ExceptionIds ex) {
		super(ex.name() + " (" + ex.getMessage() + ").");
		this.code = ex.name();
		this.error = ex.getMessage();
	}

	public CoreException(Throwable cause) {
		super(cause);
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
