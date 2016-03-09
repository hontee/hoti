package com.kuaiba.site.core.exceptions;

/**
 * 检测时异常
 * @author larry.qi
 *
 */
public class CheckedException extends CoreException {

	private static final long serialVersionUID = 5896783614065154728L;

	public CheckedException() {
		super();
	}

	public CheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CheckedException(String message) {
		super(message);
	}

	public CheckedException(Throwable cause) {
		super(cause);
	}

	public CheckedException(ExceptionIds ex) {
		super(ex);
	}
	
}
