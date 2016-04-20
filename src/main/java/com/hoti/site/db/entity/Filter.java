package com.hoti.site.db.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hoti.site.core.security.AuthzUtil;

/**
 * 用于过滤的参数
 * @author larry.qi
 *
 */
public enum Filter {

	/**
	 * 我的 f=my
	 */
	MY, 
	
	/**
	 * 最新 f=new
	 */
	NEW,
	
	/**
	 * 最热 f=hot
	 */
	HOT,
	
	/**
	 * 精选 f=pick
	 */
	PICK;
	
	private static Logger logger = LoggerFactory.getLogger(Filter.class);
	
	public static Filter parse(String f) {
		
		Filter filter = null;
		
		try {
			filter = Filter.valueOf(f.toUpperCase());
		} catch (Exception e) {
			logger.warn("解析过滤参数{}异常：{}", f, e.getMessage());
		}
		
		if (filter == null) {
			filter = AuthzUtil.isAuthorized()? Filter.MY: Filter.PICK;
		} 

		// 用户未登录的情况下f=my请求自动转换成f=like
		if (filter == Filter.MY && !AuthzUtil.isAuthorized()) {
			return Filter.PICK;
		}
		
		
		return filter;
	}

}
