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
  
  /**
   * 重构统计任务
   * @throws SecurityException
   */
  void rebuildCountTask() throws SecurityException;
  
}
