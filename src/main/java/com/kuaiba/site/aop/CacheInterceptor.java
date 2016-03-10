package com.kuaiba.site.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.kuaiba.site.core.cache.CacheIds;
import com.kuaiba.site.core.cache.CacheUtils;

/**
 * 设置缓存
 * @author larry.qi
 */
@Aspect
@Component
public class CacheInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(CacheInterceptor.class);

	@AfterReturning(pointcut = "@annotation(com.kuaiba.site.aop.UseCache)", returning = "returnValue")
	public void userCache(JoinPoint joinPoint, Object returnValue) throws Throwable {
		try {
			logger.info("returnValue: " + JSON.toJSONString(returnValue));
			MethodSignature signature = (MethodSignature)joinPoint.getSignature();
			Method method = signature.getMethod();
			UseCache cache = method.getAnnotation(UseCache.class);
			if (cache != null) {
				CacheIds key = cache.key();
				if (!CacheUtils.contains(key)) {
					CacheUtils.put(key, returnValue);
				}
			}
		} catch (Exception e) {
			logger.warn("添加缓存异常：" + JSON.toJSONString(returnValue));
		}
	}
	
	@AfterReturning(pointcut = "@annotation(com.kuaiba.site.aop.FlushCache)", returning = "returnValue")
	public void flushCache(JoinPoint joinPoint, Object returnValue) throws Throwable {
		try {
			logger.info("returnValue: " + JSON.toJSONString(returnValue));
			MethodSignature signature = (MethodSignature)joinPoint.getSignature();
			Method method = signature.getMethod();
			FlushCache cache = method.getAnnotation(FlushCache.class);
			if (cache != null) {
				/*CacheUtils.flush(useCache.key(), returnValue);*/
			}
		} catch (Exception e) {
			logger.warn("添加缓存异常：" + JSON.toJSONString(returnValue));
		}
	}

}
