package com.kuaiba.site.exceptions;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.front.result.ResultBuilder;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 业务处理异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ BusinessException.class })
    /*@ResponseStatus(code = HttpStatus.BAD_GATEWAY)*/
	public @ResponseBody Result business(BusinessException e) {
		return ResultBuilder.failed(e);
	}
	
	/**
	 * 没有访问权限
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ UnauthorizedException.class })
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public @ResponseBody Result authorization(UnauthorizedException e) {
		return ResultBuilder.failed("没有操作权限.");
	}

}
