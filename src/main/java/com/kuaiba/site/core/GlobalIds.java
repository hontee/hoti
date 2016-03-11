package com.kuaiba.site.core;

/**
 * 常量类
 * @author larry.qi
 */
public interface GlobalIds {
	
	String CHARSET = "UTF-8"; // 字符编码
	String LOGIN_USER = "kbUser"; // 登录用户
	String ADMIN_USER = "admin"; // 是否为管理员
	String REFFER = "ref=kuaiba"; // 默认设置来源

	int COOKIE_MAX_AGE = 1000 * 60 * 60 * 24 * 7; // Cookie有效期
	String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36";
	int TIMEOUT = 5000;
	
	// EhCache names
	String CFG_EHCACHE = "/META-INF/spring/ehcache.xml"; // EhCache
	String CACHE_OBJECT = "objectCache"; // 对象缓存
	String CACHE_DEFAULT = "default"; // 默认
	String CACHE_PRC = "kuaiba.passwordRetryCache"; // 密码缓存
	String CACHE_OBJECTS = "kuaiba.objects"; // 对象缓存
	String CACHE_USERS = "kuaiba.users"; // 用户缓存
}
