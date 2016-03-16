package com.kuaiba.site.core.exception;

/**
 * 运行时异常
 * 
 * @author larry.qi
 *
 */
public class BaseRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private final ErrorIDs errorId;

	public BaseRuntimeException(ErrorIDs errorId, String msg, Throwable t) {
		super(msg + ", errCode=" + errorId, t);
		this.errorId = errorId;
	}

	/**
	 *
	 * errorId contains the error id which is defined here {@link ErrorIds}.
	 */
	public BaseRuntimeException(ErrorIDs errorId, String msg) {
		super(msg + ", errCode=" + errorId);
		this.errorId = errorId;
	}

	/**
	 * Return the error id that is defined by this class {@link ErrorIds}.
	 *
	 * @return error id which is defined here {@link ErrorIds}.
	 */
	public ErrorIDs getErrorId() {
		return errorId;
	}
}
