package com.kuaiba.site.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.kuaiba.site.core.exceptions.BType;
import com.kuaiba.site.core.exceptions.BusinessException;

/**
 * 服务层工具
 * @author larry.qi
 *
 */
public class ValidUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ValidUtils.class);
	
	/**
	 * 请求参数不能为空
	 * @param objects
	 * @throws ValidationException
	 */
	public static void checkNotNull(Object...objects) {
		try {
			for (Object object : objects) {
				Preconditions.checkNotNull(object);
			}
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2001);
		}
	}
	
	/**
	 * 检测ID
	 * @param id
	 */
	public static void checkPrimaryKey(Long id) {
		try {
			Preconditions.checkNotNull(id);
			Preconditions.checkArgument(id > 0);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2001);
		}
	}
	
}
