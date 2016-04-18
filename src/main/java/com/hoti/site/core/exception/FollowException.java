package com.hoti.site.core.exception;

/**
 * 关注失败
 * 
 * @author larry.qi
 *
 */
public class FollowException extends SecurityException {

  private static final long serialVersionUID = 1L;

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public FollowException(String msg) {
    super(ErrorIDs.FOLLOW_FAILIED, msg);
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public FollowException(String msg, Exception e) {
    super(ErrorIDs.FOLLOW_FAILIED, msg, e);
  }
}
