package com.kuaiba.site.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;
import com.kuaiba.site.exceptions.ValidationException;

/**
 * 服务层工具
 * @author larry.qi
 *
 */
public class ValidUtils {
	
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
			throw new ValidationException(e);
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
			throw new ValidationException(e);
		}
	}
	
	/**
	 * 检测邮箱格式
	 * @param email
	 */
	public static void checkEmail(String email) {
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			Preconditions.checkArgument(matcher.matches());
		} catch (Exception e) {
			throw new ValidationException(e);
		}
	}
	
}
