package com.kuaiba.site.exceptions;

/**
 * 请求参数错误
 * @author larry.qi
 *
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -8678026198429920325L;

	public ValidationException(Throwable cause) {
		super("请求参数错误.", cause);
	}

}
