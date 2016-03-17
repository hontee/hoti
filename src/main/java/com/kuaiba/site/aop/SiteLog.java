package com.kuaiba.site.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作记录，用于增删改操作
 * @author larry.qi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SiteLog {
	
	/**
	 * 动作名称
	 * @return
	 */
	String action();
	
	/**
	 * 指定用于序列化的对象
	 * @return
	 */
	Class<?> clazz() default Long.class;
	
	/**
	 * 操作主表名称
	 * @return
	 */
	String table();

}
