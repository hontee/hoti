package com.kuaiba.site.interceptor;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Throwables;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.security.MemcachedUtil;
import com.kuaiba.site.core.security.ThreadUtil;

/**
 * 清除缓存
 * @author larry.qi
 */
@Aspect
@Component
public class ClearCacheInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(ClearCacheInterceptor.class);
	
	@After("@annotation(com.kuaiba.site.interceptor.ClearCache)")
	public void siteLog(JoinPoint joinPoint) throws Throwable {
		try {
			MethodSignature signature = (MethodSignature)joinPoint.getSignature();
			Method method = signature.getMethod();
			ClearCache clearCache = method.getAnnotation(ClearCache.class);
			
			if (clearCache != null) {
				final String key = clearCache.value();
				ThreadUtil.execute(new Runnable() {
					public void run() {
						try {
							MemcachedUtil.delete(key);
						} catch (SecurityException e) {
							logger.warn("清除[{}]缓存失败", key);
							logger.warn(Throwables.getStackTraceAsString(e));
						}
					}
				});
			}
		} catch (Exception e) {
			logger.warn("添加操作异常：");
			logger.warn(Throwables.getStackTraceAsString(e));
		}
	}
	
}
