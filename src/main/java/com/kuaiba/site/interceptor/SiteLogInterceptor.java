package com.kuaiba.site.interceptor;

import java.lang.reflect.Method;

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
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.db.entity.Activity;
import com.kuaiba.site.db.entity.HttpUtil;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.service.ActivityService;

/**
 * 记录操作
 * @author larry.qi
 */
@Aspect
@Component
public class SiteLogInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(SiteLogInterceptor.class);
	
	@Resource
	private ActivityService as;

	@AfterReturning(pointcut = "@annotation(com.kuaiba.site.interceptor.SiteLog)", returning = "returnValue")
	public void siteLog(JoinPoint joinPoint, Object returnValue) throws Throwable {
		try {
			logger.info("returnValue: " + JSON.toJSONString(returnValue));
			MethodSignature signature = (MethodSignature)joinPoint.getSignature();
			Method method = signature.getMethod();
			
			// 记录操作日志
			Activity record = new Activity();
			record.setCreator(AuthzUtil.getUsername());
			record.setUserType(AuthzUtil.isAdmin() ? "admin" : "user");
			
			// 判断返回状态
			if (returnValue instanceof SiteResponse) {
				SiteResponse response = (SiteResponse) returnValue;
				record.setState(response.isSuccess()? (byte)1: 0);
			}
			
			// 获取注解
			SiteLog activity = method.getAnnotation(SiteLog.class);
			
			if (activity != null) {
				record.setName(activity.action());
				record.setTbl(activity.table());
				boolean flag = true;
				
				// 获取IP和具体的描述信息
				for (Object object : joinPoint.getArgs()) {
					if (object instanceof HttpServletRequest) {
						HttpServletRequest request = (HttpServletRequest) object;
						record.setIp(HttpUtil.getIpAddr(request));
					} else if (object.getClass().equals(activity.clazz()) && flag) {
						record.setDescription(JSON.toJSONString(object));
						flag = false;
					}
				}
				
				// 保存数据
				as.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("添加操作异常：" + JSON.toJSONString(returnValue));
		}
	}
	
}
