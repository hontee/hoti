package com.kuaiba.site.core.exception;

/**
 * 读取数据异常
 * 
 * @author larry.qi
 *
 */
public class NotFoundException extends SecurityException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create an exception with an error code that maps to {@link ErrorIDs} and
	 * message text.
	 */
	public NotFoundException(String msg) {
		super(ErrorIDs.NOT_FOUND, msg);
	}

	/**
	 * Create exception with error id, message and related exception.
	 */
	public NotFoundException(String msg, Exception e) {
		super(ErrorIDs.NOT_FOUND, msg, e);
	}
}
