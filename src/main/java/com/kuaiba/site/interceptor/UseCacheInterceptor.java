package com.kuaiba.site.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 设置缓存
 * @author larry.qi
 */
@Aspect
@Component
public class UseCacheInterceptor {
	
private Logger logger = LoggerFactory.getLogger(UseCacheInterceptor.class);
	
	@Pointcut("@annotation(com.kuaiba.site.annotation.UseCache)")
	public void UseCache() {}
	
	@AfterReturning(value = "UseCache()", returning = "value")
	public void afterReturn(JoinPoint joinPoint, Object value) {
		logger.info("================Use Cache=================");
	}

}
