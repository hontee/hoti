package com.hoti.site.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 添加操作异常
 * 
 * @author larry.qi
 *
 */
public class CreateException extends SecurityException {

  private static final long serialVersionUID = 1L;
  
  private Logger logger = LoggerFactory.getLogger(CreateException.class);

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public CreateException(String msg) {
    super(ErrorIDs.CREATE_FAILIED, msg);
    logger.debug("添加失败: {}", msg);
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public CreateException(Exception e) {
    super(ErrorIDs.CREATE_FAILIED, "添加失败", e);
    logger.debug("添加失败: {}", e.getMessage());
  }
  
  /**
   * Create exception with error id, message and related exception.
   */
  public CreateException(String msg, Exception e) {
    super(ErrorIDs.CREATE_FAILIED, msg, e);
    logger.debug("{}: {}", msg, e.getMessage());
  }
}
