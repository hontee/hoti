package com.hoti.site.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST 请求异常
 * 
 * @author larry.qi
 *
 */
public class CountException extends SecurityException {

  private static final long serialVersionUID = 1L;
  
  private Logger logger = LoggerFactory.getLogger(CountException.class);

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public CountException(String msg) {
    super(ErrorIDs.COUNT_FAILIED, msg);
    logger.debug("统计失败: {}", msg);
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public CountException(Exception e) {
    super(ErrorIDs.COUNT_FAILIED, "统计失败", e);
    logger.debug("统计失败: {}", e.getMessage());
  }
  
  /**
   * Create exception with error id, message and related exception.
   */
  public CountException(String msg, Exception e) {
    super(ErrorIDs.COUNT_FAILIED, msg, e);
    logger.debug("{}: {}", msg, e.getMessage());
  }
  
}
