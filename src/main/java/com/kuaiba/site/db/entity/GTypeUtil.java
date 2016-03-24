package com.kuaiba.site.db.entity;

import java.util.Arrays;

public class GTypeUtil {

	/**
	 * 群组类型
	 */
	private final static String[] GTYPES = { "user", "topic", "org" };

	/**
	 * 验证群组性质
	 * <li>user=用户</li>
	 * <li>topic=主题</li>
	 * <li>ORG=机构</li>
	 * 
	 * @param userType
	 * @return
	 */
	public static boolean validate(String gtype) {
		return Arrays.asList(GTYPES).contains(gtype.toLowerCase());
	}
}
