package com.ikyer.site.front.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ikyer.site.rest.TaskService;

/**
 * 定时统计任务
 * 
 * @author larry.qi
 */
@Component
public class TaskController {
  
  private Logger logger = LoggerFactory.getLogger(TaskController.class);

  @Resource
  private TaskService service;
  
  /**
   * 重构所有产品和主题类别名称
   * 
   * @Task 每天凌晨2点00分触发
   */
  @Scheduled(cron = "0 0 02 * * ?")
  public void rebuildPTCategory() {
    logger.info("开始重构所有产品和主题类别名称...");
  }
  
  /**
   * 每天凌晨2点40分触发
   */
  @Scheduled(cron = "0 40 02 * * ?")
  public void rebuildCountTask() {
    logger.info("重构所有统计数据...");
    try {
      service.rebuildCountTask();
    } catch (SecurityException e) {
      e.printStackTrace();
    }
  }

}
