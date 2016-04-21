package com.hoti.site.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoti.site.front.vo.ResponseVO;

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 业务异常
   * 
   * @param e
   * @return
   */
  @ExceptionHandler({BaseException.class})
  public @ResponseBody ResponseVO handle(BaseException e) {
    return new ResponseVO(e);
  }

  /**
   * 运行时异常
   * 
   * @param e
   * @return
   */
  @ExceptionHandler({BaseRuntimeException.class})
  public @ResponseBody ResponseVO handle(BaseRuntimeException e) {
    return new ResponseVO(e);
  }

  /**
   * 未知错误
   * 
   * @param e
   * @return
   */
  @ExceptionHandler({Exception.class})
  public @ResponseBody ResponseVO handle(Exception e) {
    SecurityException ex = new SecurityException(ErrorIDs.UNKNOWN, "未知错误");
    return new ResponseVO(ex);
  }

}
