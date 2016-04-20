package com.hoti.site.front.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时统计任务
 * 
 * @author larry.qi
 */
@Component
public class CountTask {

  /**
   * 每天凌晨2点10分触发
   */
  @Scheduled(cron = "0 10 02 * * ?")
  public void countCategoryTask() {

  }

  /**
   * 每天凌晨2点40分触发
   */
  @Scheduled(cron = "0 40 02 * * ?")
  public void countGroupTask() {

  }

}
