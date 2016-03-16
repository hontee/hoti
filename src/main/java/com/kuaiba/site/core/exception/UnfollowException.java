package com.kuaiba.site.core.exception;

/**
 * 取消关注失败
 * 
 * @author larry.qi
 *
 */
public class UnfollowException extends SecurityException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create an exception with an error code that maps to {@link ErrorIDs} and
	 * message text.
	 */
	public UnfollowException(String msg) {
		super(ErrorIDs.UNFOLLOW_FAILIED, msg);
	}

	/**
	 * Create exception with error id, message and related exception.
	 */
	public UnfollowException(String msg, Exception e) {
		super(ErrorIDs.UNFOLLOW_FAILIED, msg, e);
	}
}
