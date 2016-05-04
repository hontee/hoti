package com.ikyer.site.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 删除操作异常
 * 
 * @author larry.qi
 *
 */
public class DeleteException extends SecurityException {

  private static final long serialVersionUID = 1L;
  
  private Logger logger = LoggerFactory.getLogger(DeleteException.class);

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public DeleteException(String msg) {
    super(ErrorIDs.DELETE_FAILIED, msg);
    logger.debug("删除失败: {}", msg);
  }
  
  /**
   * Create exception with error id, message and related exception.
   */
  public DeleteException(Exception e) {
    super(ErrorIDs.DELETE_FAILIED, "删除失败", e);
    logger.debug("删除失败: {}", e.getMessage());
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public DeleteException(String msg, Exception e) {
    super(ErrorIDs.DELETE_FAILIED, msg, e);
    logger.debug("{}: {}", msg, e.getMessage());
  }
}
