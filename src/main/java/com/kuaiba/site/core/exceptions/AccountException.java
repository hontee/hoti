package com.kuaiba.site.core.exceptions;

/**
 * 账户异常
 * @author larry.qi
 *
 */
public class AccountException extends CoreException {

	private static final long serialVersionUID = -4959376311502526187L;

	// 默认：登录失败
	public AccountException() {
		super(ExceptionIds.ACCOUNT_DEFAULT);
	}

	public AccountException(ExceptionIds ex) {
		super(ex);
	}
	
}
