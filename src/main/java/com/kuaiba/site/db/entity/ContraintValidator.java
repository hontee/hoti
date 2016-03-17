package com.kuaiba.site.db.entity;

import java.util.Arrays;

import com.google.common.base.Preconditions;
import com.kuaiba.site.core.exception.ValidationException;

/**
 * 服务层工具
 * 
 * @author larry.qi
 *
 */
public class ContraintValidator {

	/**
	 * 请求参数不能为空
	 * 
	 * @param objects
	 */
	public static void checkNotNull(Object... objects) throws ValidationException {
		try {
			Arrays.asList(objects).forEach((o) -> Preconditions.checkNotNull(o));
		} catch (Exception e) {
			throw new ValidationException("检验参数为空", e);
		}
	}

	/**
	 * 检测ID
	 * 
	 * @param id
	 */
	public static void checkPrimaryKey(Long id) throws ValidationException {
		try {
			Preconditions.checkArgument(id != null && id > 0);
		} catch (Exception e) {
			throw new ValidationException("无效的ID", e);
		}
	}
	
	/**
	 * 检测数组是否为空
	 * @param ids
	 * @throws ValidationException
	 */
	public static void checkArrays(String[] ids) throws ValidationException {
		if (ids == null || Arrays.asList(ids).isEmpty()) {
			throw new ValidationException("数组为空");
		}
	}
	
}
