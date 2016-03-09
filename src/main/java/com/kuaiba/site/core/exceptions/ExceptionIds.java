package com.kuaiba.site.core.exceptions;

/**
 * 错码代码
 * @author larry.qi
 *
 */
public enum ExceptionIds {
	
	/*Core*/
	SYSTEM_DEFAULT("未知错误"),
	SYSTEM_ERROR("系统错误"),
	
	/*Account*/
	ACCOUNT_DEFAULT("登录失败"),
	ACCOUNT_UNKNOWN("用户名或密码错误"),
	ACCOUNT_UNAUTHORIZATION("没有操作权限"),
	ACCOUNT_SESSION_EXPIRED("会话已过期"),
	ACCOUNT_LOCKED("帐户被锁定"),
	
	/*Checked*/
	CHECKED_DEFAULT("校验失败"),  // 默认值
	CHECKED_ID_ILLEGAL("非法的ID值"),
	CHECKED_NAME_EXISTS("名称已存在"),
	CHECKED_TITLE_EXISTS("标题已存在"),
	CHECKED_URL_EXISTS("URL已存在"),
	CHECKED_PARAMS_ERROR("请求参数错误"),
	
	/*Logic*/
	LOGIC_DEFAULT("业务处理失败"),
	LOGIC_ADD("添加操作失败"),
	LOGIC_UPDATE("更新操作失败"),
	LOGIC_DELETE("删除操作失败"),
	LOGIC_QUERY("查询操作失败"),
	LOGIC_FOLLOW("添加关注失败"),
	LOGIC_UNFOLLOW("取消关注失败");
	
	private String message;

	private ExceptionIds(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
