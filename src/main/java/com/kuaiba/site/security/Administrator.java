package com.kuaiba.site.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;

import com.kuaiba.site.GlobalIds;
import com.kuaiba.site.db.entity.User;

/**
 * 登录用户信息
 * @author larry.qi
 */
public abstract class Administrator {
	
	/**
	 * 获取登录用户
	 * @return
	 */
	public static User getUser() {
		try {
			return (User)SecurityUtils.getSubject().getSession().getAttribute(GlobalIds.LOGIN_USER);
		} catch (Exception e) {
			throw new InvalidSessionException();
		}
	}
	
	/**
	 * 获取登录用户ID
	 * @return
	 */
	public static Long getId() {
		try {
			return getUser().getId();
		} catch (Exception e) {
			throw new InvalidSessionException();
		}
	}
	
	/**
	 * 获取登录用户名称
	 * @return
	 */
	public static String getName() {
		try {
			return getUser().getName();
		} catch (Exception e) {
			throw new InvalidSessionException();
		}
	}
	
	/**
	 * 判断登录用户是否为管理员
	 * @return
	 */
	public static boolean isAdmin() {
		try {
			return getUser().getUserType() == 2;
		} catch (Exception e) {
			throw new InvalidSessionException();
		}
	}
	
	/**
	 * 判断登录用户是否为管理员
	 * @return
	 */
	public static boolean isLogin() {
		try {
			return getUser() != null;
		} catch (Exception e) {
			return false;
		}
	}
}
