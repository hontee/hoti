package com.ikyer.site.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ikyer.site.front.vo.ResponseVO;

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 业务异常
   * @param e
   * @return
   */
  @ExceptionHandler({SecurityException.class})
  public @ResponseBody ResponseVO handle(SecurityException e) {
    return new ResponseVO(e);
  }

  /**
   * 未知错误
   * @param e
   * @return
   */
  @ExceptionHandler({Exception.class})
  public @ResponseBody ResponseVO handle(Exception e) {
    SecurityException ex = new SecurityException(ErrorIDs.UNKNOWN, "未知错误");
    return new ResponseVO(ex);
  }

}
