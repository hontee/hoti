package com.ikyer.site.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

import com.ikyer.site.core.exception.SecurityException;
import com.ikyer.site.core.security.MemcachedUtil;

@Component
public class InitializerListener implements WebApplicationInitializer {

  private Logger logger = LoggerFactory.getLogger(InitializerListener.class);

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    try {
      MemcachedUtil.flush();
      logger.info("缓存初始化完成");
    } catch (SecurityException e) {
      logger.warn("缓存初始化失败：" + e.getMessage());
    }
  }

}
