package com.kuaiba.site.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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
import com.kuaiba.site.db.model.ResponseEntity;
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

	@AfterReturning(pointcut = "@annotation(com.kuaiba.site.aop.Log)", returning = "returnValue")
	public void logger(JoinPoint joinPoint, Object returnValue) throws Throwable {
		try {
			logger.info("returnValue: " + JSON.toJSONString(returnValue));
			MethodSignature signature = (MethodSignature)joinPoint.getSignature();
			Method method = signature.getMethod();
			byte state = 1;
			
			// 判断返回状态
			if (returnValue instanceof ResponseEntity) {
				ResponseEntity response = (ResponseEntity) returnValue;
				state = response.isSuccess()? (byte)1: 0;
			}
			
			// 获取注解
			Log activity = method.getAnnotation(Log.class);
			if (activity != null) {
				String action = activity.action();
				String table = activity.table();
				Class<?> clazz = activity.clazz();

				// 获取参数
				HttpServletRequest request = null;
				String json = "";
				boolean flag = true;
				List<Object> args = Arrays.asList(joinPoint.getArgs());
				
				for (Object object : args) {
					if (object instanceof HttpServletRequest) {
						request = (HttpServletRequest) object;
					} else if (object.getClass().equals(clazz) && flag) {
						json = JSON.toJSONString(object);
						flag = false;
					}
				}
				
				// 记录日志
				as.logger(action, table, json, state, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("添加操作异常：" + JSON.toJSONString(returnValue));
		}
	}
	
}
