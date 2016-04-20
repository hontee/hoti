package com.hoti.site.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取数据异常
 * 
 * @author larry.qi
 *
 */
public class FindException extends SecurityException {

  private static final long serialVersionUID = 1L;
  
  private Logger logger = LoggerFactory.getLogger(FindException.class);

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public FindException(String msg) {
    super(ErrorIDs.READ_FAILIED, msg);
    logger.debug("查询失败: {}", msg);
  }
  
  /**
   * Create exception with error id, message and related exception.
   */
  public FindException(Exception e) {
    super(ErrorIDs.READ_FAILIED, "查询失败", e);
    logger.debug("查询失败: {}", e.getMessage());
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public FindException(String msg, Exception e) {
    super(ErrorIDs.READ_FAILIED, msg, e);
    logger.debug("{}: {}", msg, e.getMessage());
  }
}
