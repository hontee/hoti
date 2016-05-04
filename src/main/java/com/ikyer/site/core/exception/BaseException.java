package com.ikyer.site.core.exception;

/**
 * 检查异常基类
 * 
 * @author larry.qi
 */
public abstract class BaseException extends Exception implements StandardException {

  private static final long serialVersionUID = 1L;
  private final ErrorIDs errorId;

  /**
   * Create exception containing error code and message.
   */
  BaseException(ErrorIDs errorId, String msg) {
    super(msg);
    this.errorId = errorId;
  }

  /**
   * Create exception containing error code, message and previous exception.
   */
  BaseException(ErrorIDs errorId, String msg, Throwable t) {
    super(msg, t);
    this.errorId = errorId;
  }

  /**
   * Return the error id that is defined by this class {@link ErrorIDs}.
   *
   * @return error id which is defined here {@link ErrorIDs}.
   */
  public ErrorIDs getErrorId() {
    return errorId;
  }
  
}
