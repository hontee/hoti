package com.ikyer.site.front.vo;

import java.io.Serializable;

import com.ikyer.site.core.exception.SecurityException;

public class ResponseVO implements Serializable {

  private static final long serialVersionUID = -8290882370006935102L;

  private boolean success = true; // 成功标记
  private Error error; // 错误
  private Object result; // 成功

  protected ResponseVO() {}

  public ResponseVO(Object result) {
    this.setResult(result);
  }

  public ResponseVO(SecurityException e) {
    this.setError(e.getErrorId().name(), e.getMessage());
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
    if (success) {
      this.error = null;
    } else {
      this.result = null;
    }
  }

  public Error getError() {
    return error;
  }

  public void setError(String code, String message) {
    this.setSuccess(false);
    this.error = new Error();
    error.setCode(code);
    error.setMessage(message);
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.setSuccess(true);
    this.result = result;
  }

  /**
   * 错误信息
   * @author larry.qi
   */
  public static class Error implements Serializable {

    private static final long serialVersionUID = -4425094276756966685L;

    private String code; // 错误码

    private String message; // 错误信息

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }

}
