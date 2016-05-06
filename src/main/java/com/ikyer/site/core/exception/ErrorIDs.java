package com.ikyer.site.core.exception;

/**
 * 错码码
 * @author larry.qi
 */
public enum ErrorIDs {

  /**
   * 未知错误
   */
  UNKNOWN,

  /**
   * 帐户异常
   */
  ACCOUNT_ERROR,

  /**
   * 授权失败
   */
  AUTHZ_FAILIED,

  /**
   * 缓存异常
   */
  CACHE_ERROR,

  /**
   * 创建失败
   */
  CREATE_FAILIED,

  /**
   * 删除失败
   */
  DELETE_FAILIED,

  /**
   * 关注失败
   */
  FOLLOW_FAILIED,

  /**
   * 没有找到
   */
  NOT_FOUND,

  /**
   * 密码异常
   */
  PASSWORD_ERROR,

  /**
   * 读取数据失败
   */
  READ_FAILIED,

  /**
   * 统计失败
   */
  COUNT_FAILIED,

  /**
   * 取消关注失败
   */
  UNFOLLOW_FAILIED,

  /**
   * 更新失败
   */
  UPDATE_FAILIED,

  /**
   * 验证错误
   */
  VALIDATION_ERROR

}
