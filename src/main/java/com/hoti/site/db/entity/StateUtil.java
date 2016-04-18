package com.hoti.site.db.entity;

import java.util.Arrays;

/**
 * 通用状态工具
 * @author larry.qi
 *
 */
public class StateUtil {

	/**
	 * 状态 
	 * <li>0=禁用</li>
	 * <li>1=启用</li>
	 * <li>2=锁定 , 仅用于用户状态</li>
	 * <li>3=删除 , 软删除状态</li>
	 */
	private final static Byte[] STATES = {0, 1, 2, 3};
	
	/**
	 * 验证状态
	 * @param state
	 * @return
	 */
	public static boolean validate(Byte state) {
		return Arrays.asList(STATES).contains(state);
	}
	
}
