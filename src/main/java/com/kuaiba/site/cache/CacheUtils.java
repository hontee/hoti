package com.kuaiba.site.cache;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 对象缓存工具
 * @author larry.qi
 *
 */
public abstract class CacheUtils {
	
	private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);
	
	/**
	 * 获取缓存对象
	 * @return
	 * @exception CacheException
	 */
	public static Cache getCache() {
		try {
			URL url = CacheUtils.class.getResource(CacheIds.CFG_EHCACHE);
			CacheManager cacheManager = CacheManager.create(url);
			return cacheManager.getCache(CacheIds.CACHE_OBJECT);
		} catch (CacheException e) {
			logger.warn("缓存异常：" + Throwables.getStackTraceAsString(e));
			throw new CacheException("缓存异常", e);
		} catch (IllegalStateException e) {
			logger.warn("缓存异常，非法状态：" + Throwables.getStackTraceAsString(e));
			throw new CacheException("缓存异常，非法状态", e);
		} catch (ClassCastException e) {
			logger.warn("缓存异常，类型转换错误：" + Throwables.getStackTraceAsString(e));
			throw new CacheException("缓存异常，，类型转换错误", e);
		}
	}
	
	/**
	 * 添加缓存
	 * @param key
	 * @param value
	 */
	public static void put(String key, Object value) {
		try {
			getCache().put(new Element(key, value));
		} catch (Exception e) {
			logger.warn("缓存异常：" + Throwables.getStackTraceAsString(e));
		}
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return object or null
	 */
	public static Object get(String key) {
		try {
			return getCache().get(key).getObjectValue();
		} catch (Exception e) {
			logger.warn("缓存异常：" + Throwables.getStackTraceAsString(e));
		}
		return null;
	}
	
	/**
	 * 删除缓存
	 * @param key
	 */
	public static void remove(String key) {
		try {
			getCache().remove(key);
		} catch (Exception e) {
			logger.warn("缓存异常：" + Throwables.getStackTraceAsString(e));
		}
	}
	
	/**
	 * 刷新缓存
	 * @param key
	 * @param value
	 */
	public static void flush(String key, Object value) {
		if (contains(key)) {
			remove(key);
		}
		put(key, value);
	}
	
	/**
	 * 是否存在: key
	 * @param key
	 * @return
	 */
	public static boolean contains(String key) {
		try {
			return getCache().getKeys().contains(key);
		} catch (Exception e) {
			logger.warn("缓存异常：" + Throwables.getStackTraceAsString(e));
		}
		return false;
	}

}
