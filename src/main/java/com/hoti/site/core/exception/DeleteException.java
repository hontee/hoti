package com.hoti.site.core.exception;

/**
 * 删除操作异常
 * 
 * @author larry.qi
 *
 */
public class DeleteException extends SecurityException {

  private static final long serialVersionUID = 1L;

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public DeleteException(String msg) {
    super(ErrorIDs.DELETE_FAILIED, msg);
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public DeleteException(String msg, Exception e) {
    super(ErrorIDs.DELETE_FAILIED, msg, e);
  }
}
