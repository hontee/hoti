package com.kuaiba.site.db.entity;

import java.util.Arrays;

public class StateAuditUtil {
	
	/**
	 * 审核状态 
	 * <li>1=未审核</li>
	 * <li>2=审核通过</li>
	 * <li>3=审核拒绝</li>
	 */
	private final static Byte[] STATES = {1, 2, 3};
	
	/**
	 * 验证状态
	 * @param state
	 * @return
	 */
	public static boolean validate(Byte state) {
		return Arrays.asList(STATES).contains(state);
	}

}
