package com.kuaiba.site.aop.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 刷新缓存
 * @author larry.qi
 */
@Aspect
@Component
public class FlushCacheInterceptor {

	private Logger logger = LoggerFactory.getLogger(FlushCacheInterceptor.class);
	
	@Pointcut("@annotation(com.kuaiba.site.aop.annotation.FlushCache)")
	public void FlushCache() {}
	
	@After("FlushCache()")
	public void after(JoinPoint joinPoint) {
		logger.info("=======================================");
	}
}
