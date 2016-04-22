package com.hoti.site.rest;

/**
 * 定时任务服务
 * 
 * @author larry.qi
 *
 */
public interface TaskService {

  /**
   * 重构所有产品和主题的类别名称
   * 
   * @throws SecurityException
   */
  void rebuildPTCategory() throws SecurityException;
  
}
