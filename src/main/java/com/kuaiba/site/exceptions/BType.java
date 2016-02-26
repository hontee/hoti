package com.kuaiba.site.exceptions;

public enum BType {
	
	/*用户相关*/
	KB1001("登录失败"),
	KB1002("用户名或密码错误"),
	KB1003("没有权限"),
	
	/*业务相关*/
	KB2001("请求参数错误"),
	KB2002("名称已存在"),
	KB2003("业务处理异常"),
	
	/*系统相关*/
	KB3001("系统错误");
	
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
