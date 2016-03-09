package com.kuaiba.site.service.utils;

import java.util.Arrays;

import com.google.common.base.Preconditions;
import com.kuaiba.site.core.exceptions.CheckedException;
import com.kuaiba.site.core.exceptions.ExceptionIds;

/**
 * 服务层工具
 * 
 * @author larry.qi
 *
 */
public class ValidUtils {

	/**
	 * 请求参数不能为空
	 * 
	 * @param objects
	 */
	public static void checkNotNull(Object... objects) 
			throws CheckedException {
		try {
			Arrays.asList(objects).forEach((o) -> 
				Preconditions.checkNotNull(o)
			);
		} catch (Exception e) {
			throw new CheckedException(ExceptionIds.CHECKED_PARAMS_ERROR);
		}
	}

	/**
	 * 检测ID
	 * 
	 * @param id
	 */
	public static void checkPrimaryKey(Long id) 
			throws CheckedException {
		try {
			Preconditions.checkArgument(id != null && id > 0);
		} catch (Exception e) {
			throw new CheckedException(ExceptionIds.CHECKED_ID_ILLEGAL);
		}
	}

}
