package com.ikyer.site.core.exception;

/**
 * 安全管理异常: 继承{@link BaseException}
 * 
 * @author larry.qi
 */
public class SecurityException extends Exception implements StandardException {

  private static final long serialVersionUID = 1L;
  private final ErrorIDs errorId;

  /**
   * Create exception with error id.
   */
  public SecurityException(ErrorIDs errorId) {
    this.errorId = errorId;
  }

  /**
   * Create exception with error id and message.
   */
  public SecurityException(ErrorIDs errorId, String msg) {
    super(msg);
    this.errorId = errorId;
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public SecurityException(ErrorIDs errorId, Throwable t) {
    super(t);
    this.errorId = errorId;
  }

  @Override
  public ErrorIDs getErrorId() {
    return errorId;
  }

}
