package com.kuaiba.site.front.taglib;

import javax.servlet.jsp.JspException;

import com.kuaiba.site.core.security.AuthzUtil;

/**
 * 头信息模板
 * @author larry.qi
 */
public class HeaderTag extends AbstractTagSupport {

	private static final long serialVersionUID = 1L;
	private String title;  // 页面标题
	private String description = ""; // 页面描述内容
	private String keywords = ""; // 页面标签
	private String q = ""; // 搜索
	
	@Override
	public int doStartTag() throws JspException {
		addAttribute("title", title);
		addAttribute("description", description);
		addAttribute("keywords", keywords);
		addAttribute("roles", AuthzUtil.isAdmin()? "admin": "user");
		addAttribute("q", q);
		addAttribute("user", AuthzUtil.isAuthorized()? AuthzUtil.getUsername(): "");
		super.render("header.ftl");
		return super.doStartTag();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

}
