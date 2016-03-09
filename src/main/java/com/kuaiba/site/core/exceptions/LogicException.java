package com.kuaiba.site.core.exceptions;

/**
 * 业务逻辑异常
 * @author larry.qi
 *
 */
public class LogicException extends CoreException {

	private static final long serialVersionUID = 4979290150393649066L;

	// 默认：业务处理失败
	public LogicException() {
		super(ExceptionIds.LOGIC_DEFAULT);
	}

	public LogicException(ExceptionIds ex) {
		super(ex);
	}
	
}
