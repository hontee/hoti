package com.kuaiba.site.front.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.service.Countable;

/**
 * 定时统计任务
 * @author larry.qi
 */
@Component
public class CountTask {
	
	private Logger logger = LoggerFactory.getLogger(CountTask.class);

	@Resource
	private Countable countable;

	/**
	 * 每天凌晨2点触发
	 */
	@Scheduled(cron = "0 0 02 * * ?")
	public void countDomainTask() {
		try {
			countable.countDomainTask();
		} catch (SecurityException e) {
			logger.warn("执行统计领域任务失败：{}", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 每天凌晨2点10分触发
	 */
	@Scheduled(cron = "0 10 02 * * ?")
	public void countCategoryTask() {
		try {
			countable.countCategoryTask();
		} catch (SecurityException e) {
			logger.warn("执行统计分类任务失败：{}", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 每天凌晨2点40分触发
	 */
	@Scheduled(cron = "0 40 02 * * ?")
	public void countGroupTask() {
		try {
			countable.countGroupTask();
		} catch (SecurityException e) {
			logger.warn("执行统计群组任务失败：{}", e.getMessage());
			e.printStackTrace();
		}
	}
	
}
