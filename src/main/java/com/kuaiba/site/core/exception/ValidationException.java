package com.kuaiba.site.core.exception;

/**
 * 验证数据异常
 * 
 * @author larry.qi
 *
 */
public class ValidationException extends SecurityException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create an exception with an error code that maps to {@link ErrorIDs} and
	 * message text.
	 */
	public ValidationException(String msg) {
		super(ErrorIDs.VALIDATION_ERROR, msg);
	}

	/**
	 * Create exception with error id, message and related exception.
	 */
	public ValidationException(String msg, Exception e) {
		super(ErrorIDs.VALIDATION_ERROR, msg, e);
	}
	
}
