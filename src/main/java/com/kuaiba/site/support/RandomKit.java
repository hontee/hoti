package com.kuaiba.site.support;

import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;

public class RandomKit {
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getRandomUUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 获取随机点击率
	 * @return
	 */
	public static int getRandomHit() {
		return RandomUtils.nextInt(0, 1000);
	}
}
