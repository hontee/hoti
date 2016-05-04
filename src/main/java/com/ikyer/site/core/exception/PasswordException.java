package com.ikyer.site.core.exception;

/**
 * 密码管理异常
 * 
 * @author larry.qi
 *
 */
public class PasswordException extends SecurityException {

  private static final long serialVersionUID = 1L;

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public PasswordException(String msg) {
    super(ErrorIDs.PASSWORD_ERROR, msg);
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public PasswordException(String msg, Exception e) {
    super(ErrorIDs.PASSWORD_ERROR, msg, e);
  }

}
