package com.hoti.site.front.controller;

import javax.ws.rs.core.Response;

import org.springframework.web.context.request.WebRequest;

import com.hoti.site.core.exception.BaseException;
import com.hoti.site.core.exception.ErrorIDs;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.front.vo.ResponseVO;

/**
 * 控制器基类
 * 
 * @author larry.qi
 *
 */
public abstract class BaseController {
  /**
   * 判断是否为AJAX请求
   * 
   * @param webRequest
   * @return
   */
  public boolean isAjaxRequest(WebRequest webRequest) {
    String requestedWith = webRequest.getHeader("X-Requested-With");
    return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
  }

  /**
   * 判断是否为AJAX上传
   * 
   * @param webRequest
   * @return
   */
  public boolean isAjaxUploadRequest(WebRequest webRequest) {
    return webRequest.getParameter("ajaxUpload") != null;
  }

  /**
   * 重定向
   * 
   * @param path
   * @return
   */
  public String redirect(String path) {
    return "redirect:".concat(path);
  }

  /**
   * 响应成功
   * @return
   */
  public ResponseVO buildResponse() {
    Response response = Response.ok().build();
    return new ResponseVO(response.getStatusInfo());
  }

  /**
   * 响应成功
   * 
   * @param entity
   * @return
   */
  public ResponseVO buildResponse(Object result) {
    Response response = Response.ok(result).build();
    return new ResponseVO(response.getEntity());
  }

  /**
   * 响应失败
   * @param message 错误描述
   * @param errorId 错误码
   * @return
   */
  public ResponseVO buildResponse(ErrorIDs errorId, String message) {
    return new ResponseVO(new SecurityException(errorId, message));
  }

  /**
   * 响应失败
   * @param e 异常类型
   * @return
   */
  public ResponseVO buildResponse(BaseException e) {
    return new ResponseVO(e);
  }
  
}
