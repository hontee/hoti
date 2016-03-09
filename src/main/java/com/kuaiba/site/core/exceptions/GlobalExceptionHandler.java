package com.kuaiba.site.core.exceptions;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuaiba.site.core.utils.AjaxResponse;
import com.kuaiba.site.core.utils.AjaxUtils;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 业务处理异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ CoreException.class })
	public @ResponseBody AjaxResponse core(CoreException e) {
		return AjaxUtils.failed(e);
	}
	
	/**
	 * 没有访问权限
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	public @ResponseBody AjaxResponse authorization(UnauthorizedException e) {
		return AjaxUtils.failed("没有权限");
	}
	
	/**
	 * 运行时异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ RuntimeException.class })
	public @ResponseBody AjaxResponse runtime(RuntimeException e) {
		return AjaxUtils.failed("系统错误");
	}

}
