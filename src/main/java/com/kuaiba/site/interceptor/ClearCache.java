package com.kuaiba.site.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.kuaiba.site.service.impl.CacheMgrImpl;

/**
 * 清除缓存
 * @author larry.qi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ClearCache {
	
	/**
	 * 需要清除的key: #{@link CacheMgrImpl}
	 * @return
	 */
	String value();

}
