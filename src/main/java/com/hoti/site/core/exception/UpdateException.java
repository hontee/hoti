package com.hoti.site.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 更新操作异常
 * 
 * @author larry.qi
 *
 */
public class UpdateException extends SecurityException {

  private static final long serialVersionUID = 1L;
  
  private Logger logger = LoggerFactory.getLogger(UpdateException.class);

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public UpdateException(String msg) {
    super(ErrorIDs.UPDATE_FAILIED, msg);
    logger.debug("更新失败：{}", msg);
  }
  
  /**
   * Create exception with error id, message and related exception.
   */
  public UpdateException(Exception e) {
    super(ErrorIDs.UPDATE_FAILIED, "更新失败", e);
    logger.debug("更新失败：{}", e.getMessage());
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public UpdateException(String msg, Exception e) {
    super(ErrorIDs.UPDATE_FAILIED, msg, e);
    logger.debug("{}: {}", msg, e.getMessage());
  }
}
