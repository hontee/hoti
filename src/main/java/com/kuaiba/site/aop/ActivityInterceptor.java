package com.kuaiba.site.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.kuaiba.site.front.vo.BaseVO;
import com.kuaiba.site.service.ActivityService;

/**
 * 记录操作
 * @author larry.qi
 */
@Aspect
@Component
public class ActivityInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(ActivityInterceptor.class);
	
	@Resource
	private ActivityService as;

	@AfterReturning(pointcut = "@annotation(com.kuaiba.site.aop.Activity)", returning = "returnValue")
	public void activity(JoinPoint joinPoint, Object returnValue) throws Throwable {
		try {
			logger.info("returnValue: " + JSON.toJSONString(returnValue));
			MethodSignature signature = (MethodSignature)joinPoint.getSignature();
			Method method = signature.getMethod();
			
			// 拿到注解
			Log activity = method.getAnnotation(Log.class);
			if (activity != null) {
				String action = activity.action();
				String table = activity.table();

				// 获取参数
				HttpServletRequest request = null;
				String json = null;
				Parameter[] params = method.getParameters();
				for (Parameter p : params) {
					Object object = p.getType();
					if (object instanceof HttpServletRequest) {
						request = (HttpServletRequest) object;
					} else if (object instanceof BaseVO) {
						json = JSON.toJSONString(object);
					} else if (object instanceof Long) {
						json = object.toString();
					}
				}
				
				as.logger(action, table, json, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("添加操作异常：" + JSON.toJSONString(returnValue));
		}
	}
	
}
