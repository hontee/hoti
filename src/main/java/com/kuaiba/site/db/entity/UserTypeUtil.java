package com.kuaiba.site.db.entity;

import java.util.Arrays;

/**
 * 用户类型工具
 * @author larry.qi
 */
public class UserTypeUtil {
	
	/**
	 * 用户类型
	 * <li>user=普通用户</li>
	 * <li>ADMIN=管理员</li>
	 */
	private final static String[] USER_TYPES = {"user", "admin"};
	
	/**
	 * 用户类型
	 * <li>1=普通用户</li>
	 * <li>2=管理员</li>
	 */
	private final static Byte[] userTypes = {1, 2};
	
	/**
	 * 验证用户类型
	 * @param userType
	 * @return
	 */
	public static boolean validate(String userType) {
		return Arrays.asList(USER_TYPES).contains(userType);
	}
	
	/**
	 * 验证用户类型
	 * @param userType
	 * @return
	 */
	public static boolean validate(Byte userType) {
		return Arrays.asList(userTypes).contains(userType);
	}

}
