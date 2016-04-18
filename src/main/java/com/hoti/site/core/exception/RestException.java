package com.hoti.site.core.exception;

/**
 * REST 请求异常
 * 
 * @author larry.qi
 *
 */
public class RestException extends SecurityException {

  private static final long serialVersionUID = 1L;

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public RestException(String msg) {
    super(ErrorIDs.REST_FAILIED, msg);
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public RestException(String msg, Exception e) {
    super(ErrorIDs.REST_FAILIED, msg, e);
  }
}
