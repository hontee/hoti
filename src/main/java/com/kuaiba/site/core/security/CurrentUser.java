package com.kuaiba.site.core.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.kuaiba.site.core.GlobalIds;
import com.kuaiba.site.db.entity.User;

/**
 * 登录用户信息
 * @author larry.qi
 */
public interface CurrentUser {
	
	static Logger logger = LoggerFactory.getLogger(CurrentUser.class);
	
	static Long UN_LOGIN_USER_ID = -1L;
	
	/**
	 * 获取当前登录用户, 未登录则返回 NULL
	 * @return
	 */
	public static User getCurrentUser() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Object principal = subject.getSession().getAttribute(GlobalIds.LOGIN_USER);
			
			if (principal == null || !(principal instanceof User)) {
				return null;
			}
			
			return (User) principal;
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
		}
		
		return null;
	}
	
	/**
	 * 获取当前登录用户名, 如果当前用户未登录则返回 "anonymous".
	 * @return
	 */
	public static String getCurrentUserName() {
		if (!isLogin()) {
			return "anonymous";
		}
		
		return getCurrentUser().getName();
	}
	
	/**
	 * 取得当前用户的登录id, 如果当前用户未登录则返回 UN_LOGIN_USER_ID.
	 */
	public static Long getCurrentUserId() {
		if (!isLogin()) {
			return UN_LOGIN_USER_ID;
		}
		
		return getCurrentUser().getId();
	}
	
	/**
	 * 当前用户是否登录, 未登录返回false
	 * @return
	 */
	public static boolean isLogin() {
		return getCurrentUser() != null;
	}
	
	/**
	 * 用户是否为管理员, 未登录则返回false
	 * @return
	 */
	public static boolean isAdmin() {
		if (!isLogin()) {
			return false;
		}
		
		return getCurrentUser().getUserType() == 2L; // 管理员
	}
	
}
