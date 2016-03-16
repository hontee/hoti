package com.kuaiba.site.core.exception;

/**
 * 更新操作异常
 * 
 * @author larry.qi
 *
 */
public class UpdateException extends SecurityException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create an exception with an error code that maps to {@link ErrorIDs} and
	 * message text.
	 */
	public UpdateException(String msg) {
		super(ErrorIDs.UPDATE_FAILIED, msg);
	}

	/**
	 * Create exception with error id, message and related exception.
	 */
	public UpdateException(String msg, Exception e) {
		super(ErrorIDs.UPDATE_FAILIED, msg, e);
	}
}
