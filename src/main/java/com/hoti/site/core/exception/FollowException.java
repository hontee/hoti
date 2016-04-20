package com.hoti.site.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 关注失败
 * 
 * @author larry.qi
 *
 */
public class FollowException extends SecurityException {

  private static final long serialVersionUID = 1L;
  
  private Logger logger = LoggerFactory.getLogger(FollowException.class);

  /**
   * Create an exception with an error code that maps to {@link ErrorIDs} and message text.
   */
  public FollowException(String msg) {
    super(ErrorIDs.FOLLOW_FAILIED, msg);
    logger.debug("关注操作失败: {}", msg);
  }
  
  /**
   * Create exception with error id, message and related exception.
   */
  public FollowException(Exception e) {
    super(ErrorIDs.FOLLOW_FAILIED, "关注操作失败", e);
    logger.debug("关注操作失败: {}", e.getMessage());
  }

  /**
   * Create exception with error id, message and related exception.
   */
  public FollowException(String msg, Exception e) {
    super(ErrorIDs.FOLLOW_FAILIED, msg, e);
    logger.debug("{}: {}", msg, e.getMessage());
  }
}
