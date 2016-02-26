package com.kuaiba.site.exceptions;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.front.result.ResultBuilder;

@ControllerAdvice
public class GlobalException {
	
	/**
	 * 业务处理异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ BusinessException.class })
	public @ResponseBody Result core(BusinessException e) {
		return ResultBuilder.failed(e);
	}
	
	/**
	 * 没有访问权限
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	public @ResponseBody Result authorization(UnauthorizedException e) {
		return ResultBuilder.failed("没有权限");
	}
	
	/**
	 * 运行时异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ RuntimeException.class })
	public @ResponseBody Result runtime(RuntimeException e) {
		return ResultBuilder.failed("系统错误");
	}

}
