package com.kuaiba.site.core.exceptions;

/**
 * 账户异常
 * @author larry.qi
 *
 */
public class AccountException extends CoreException {

	private static final long serialVersionUID = -4959376311502526187L;

	public AccountException() {
		super();
	}

	public AccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountException(String message) {
		super(message);
	}

	public AccountException(Throwable cause) {
		super(cause);
	}

	public AccountException(ExceptionIds ex) {
		super(ex);
	}
	
}
