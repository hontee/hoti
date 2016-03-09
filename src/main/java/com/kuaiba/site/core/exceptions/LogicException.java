package com.kuaiba.site.core.exceptions;

/**
 * 业务逻辑异常
 * @author larry.qi
 *
 */
public class LogicException extends CoreException {

	private static final long serialVersionUID = 4979290150393649066L;

	public LogicException() {
		super();
	}

	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicException(String message) {
		super(message);
	}

	public LogicException(Throwable cause) {
		super(cause);
	}

	public LogicException(ExceptionIds ex) {
		super(ex);
	}
	
}
