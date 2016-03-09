package com.kuaiba.site.core.exceptions;

/**
 * 检测时异常
 * @author larry.qi
 *
 */
public class CheckedException extends CoreException {

	private static final long serialVersionUID = 5896783614065154728L;

	// 默认：校验失败
	public CheckedException() {
		super(ExceptionIds.CHECKED_DEFAULT);
	}

	public CheckedException(ExceptionIds ex) {
		super(ex);
	}
	
}
