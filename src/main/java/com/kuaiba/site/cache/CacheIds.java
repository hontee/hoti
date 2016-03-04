package com.kuaiba.site.cache;

/**
 * 缓存配置
 * CFG_: 配置
 * CACHE_: 缓存管理
 * KEY_: Keys管理
 * 
 * @author larry.qi
 */
public interface CacheIds {
	
	String CFG_EHCACHE = "/META-INF/spring/ehcache.xml";
	String CACHE_OBJECT = "objectCache";
	String KEY_USER = "keyUser";
}
