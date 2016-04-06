package com.kuaiba.site.core.security;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

import com.kuaiba.site.core.exception.CacheException;

@Component
public class KuaibaInitializer implements WebApplicationInitializer {
	
	private Logger logger = LoggerFactory.getLogger(KuaibaInitializer.class);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		try {
			MemcachedUtil.flush();
			logger.info("缓存初始化完成");
		} catch (CacheException e) {
			logger.warn("缓存初始化失败：" + e.getMessage());
		}
	}

}
