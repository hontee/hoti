package com.kuaiba.site.exceptions;

/**
 * 业务处理异常
 * @author larry.qi
 *
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 3146646789251536100L;

	public BusinessException(Throwable cause) {
		super("业务处理异常.", cause);
	}

}
