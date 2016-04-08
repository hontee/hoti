package com.kuaiba.site.core.exception;

/**
 * 标准异常接口：约束错误码
 * 
 * @author larry.qi
 *
 */
public interface StandardException {

  /**
   * 获取错误码
   * 
   * @return 错误码 {@link ErrorIDs}
   */
  ErrorIDs getErrorId();
}
