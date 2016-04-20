package com.hoti.site.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoti.site.db.entity.SiteResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 业务异常
   * 
   * @param e
   * @return
   */
  @ExceptionHandler({BaseException.class})
  public @ResponseBody SiteResponse handle(BaseException e) {
    return new SiteResponse(e);
  }

  /**
   * 运行时异常
   * 
   * @param e
   * @return
   */
  @ExceptionHandler({BaseRuntimeException.class})
  public @ResponseBody SiteResponse handle(BaseRuntimeException e) {
    return new SiteResponse(e);
  }

  /**
   * 未知错误
   * 
   * @param e
   * @return
   */
  @ExceptionHandler({Exception.class})
  public @ResponseBody SiteResponse handle(Exception e) {
    SecurityException ex = new SecurityException(ErrorIDs.UNKNOWN, "未知错误");
    return new SiteResponse(ex);
  }

}
