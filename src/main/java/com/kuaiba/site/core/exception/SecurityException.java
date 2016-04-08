package com.kuaiba.site.core.exception;

/**
 * 安全管理异常: 继承{@link BaseException}
 * 
 * @author larry.qi
 */
public class SecurityException extends BaseException {

  private static final long serialVersionUID = 1L;

  /**
   * Create exception with error id and message.
   */
  public SecurityException(ErrorIDs errorId, String msg) {
    super(errorId, msg);
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public SecurityException(ErrorIDs errorId, String msg, Exception e) {
    super(errorId, msg, e);
  }
}
