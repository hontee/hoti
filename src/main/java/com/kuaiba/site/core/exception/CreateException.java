package com.kuaiba.site.core.exception;

/**
 * 添加操作异常
 * 
 * @author larry.qi
 *
 */
public class CreateException extends SecurityException {

  private static final long serialVersionUID = 1L;

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public CreateException(String msg) {
    super(ErrorIDs.CREATE_FAILIED, msg);
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public CreateException(String msg, Exception e) {
    super(ErrorIDs.CREATE_FAILIED, msg, e);
  }
}
