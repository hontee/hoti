package com.kuaiba.site.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.kuaiba.site.core.cache.CacheIDs;

/**
 * 刷新缓存
 * @author larry.qi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface FlushCache {
	
	/**
	 * 需要刷新的缓存key
	 * @return
	 */
	CacheIDs key();
	

}
