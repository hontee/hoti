package com.kuaiba.site.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;

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

}
