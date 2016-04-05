package com.kuaiba.site.front.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.service.DomainService;

/**
 * 头信息模板
 * @author larry.qi
 */
public class HeaderTag extends AbstractTagSupport {
	
	private Logger logger = LoggerFactory.getLogger(HeaderTag.class);

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
		
		WebApplicationContext app= WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		DomainService domainService = (DomainService)app.getBean("domainServiceImpl");
		
		try {
			DomainExample oe = new DomainExample();
			oe.createCriteria().andStateEqualTo((byte)1);
			oe.setOrderByClause("weight DESC");
			List<Domain> records = domainService.findAllWithCates(oe);
			addAttribute("records", records);
		} catch (SecurityException e) {
			logger.warn("加载分类菜单失败：{}", e.getMessage());
		}
		
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
