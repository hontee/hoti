package com.hoti.site.core.exception;

/**
 * 缓存管理异常
 * 
 * @author larry.qi
 *
 */
public class CacheException extends SecurityException {

  private static final long serialVersionUID = 1L;

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public CacheException(String msg) {
    super(ErrorIDs.CACHE_ERROR, msg);
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public CacheException(String msg, Exception e) {
    super(ErrorIDs.CACHE_ERROR, msg, e);
  }
}
