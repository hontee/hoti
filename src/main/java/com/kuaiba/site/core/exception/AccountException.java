package com.kuaiba.site.core.exception;

/**
 * 用户账号异常
 * 
 * @author larry.qi
 *
 */
public class AccountException extends SecurityException {

  private static final long serialVersionUID = 1L;

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public AccountException(String msg) {
    super(ErrorIDs.ACCOUNT_ERROR, msg);
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public AccountException(String msg, Exception e) {
    super(ErrorIDs.ACCOUNT_ERROR, msg, e);
  }

}
