package com.kuaiba.site.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.kuaiba.site.cache.CacheIds;

/**
 * 设置缓存
 * @author larry.qi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface UseCache {
	
	/**
	 * 需要设置的缓存key
	 * @return
	 */
	CacheIds key();

}
