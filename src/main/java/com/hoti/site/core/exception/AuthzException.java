package com.hoti.site.core.exception;

/**
 * 权限认证异常
 * 
 * @author larry.qi
 *
 */
public class AuthzException extends SecurityException {

  private static final long serialVersionUID = 1L;

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public AuthzException(String msg) {
    super(ErrorIDs.AUTHZ_FAILIED, msg);
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public AuthzException(String msg, Exception e) {
    super(ErrorIDs.AUTHZ_FAILIED, msg, e);
  }

}
