package com.kuaiba.site.db.entity;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.base.Preconditions;
import com.kuaiba.site.core.exception.ValidationException;

/**
 * 服务层工具
 * 
 * @author larry.qi
 *
 */
public class VUtil {

	/**
	 * 请求参数不能为空
	 * 
	 * @param objects
	 */
	public static void assertNotNull(Object... objects) throws ValidationException {
		try {
			Arrays.asList(objects).forEach((o) -> Preconditions.checkNotNull(o));
		} catch (Exception e) {
			throw new ValidationException("检验参数为空", e);
		}
	}

	/**
	 * 检测数组是否为空
	 * @param ids
	 * @throws ValidationException
	 */
	public static void assertNotNull(String[] ids) throws ValidationException {
		if (ArrayUtils.isEmpty(ids)) {
			throw new ValidationException("数组为空");
		}
	}
	
}
