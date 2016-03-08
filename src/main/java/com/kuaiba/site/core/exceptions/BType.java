package com.kuaiba.site.core.exceptions;

public enum BType {
	
	/*用户相关*/
	LOGIN_FAILURE("登录失败"),
	USER_UNKNOWN("用户名或密码错误"),
	UNAUTHORIZATION("没有权限"),
	INVALID_SESSION("会话已过期"),
	
	/*业务相关*/
	PARAMETERS_ERROR("请求参数错误"),
	NAME_EXISTS_ERROR("名称已存在"),
	BUSINESS_ERROR("业务处理异常"),
	
	/*系统相关*/
	SYSTEM_ERROR("系统错误");
	
	private String desc;

	private BType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
