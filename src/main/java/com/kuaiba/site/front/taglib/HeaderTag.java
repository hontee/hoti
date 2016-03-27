package com.kuaiba.site.front.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;

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

	private static final long serialVersionUID = 4136719896030975233L;

	private String title;  // 页面标题
	private String description = ""; // 页面描述内容
	private String keywords = ""; // 页面标签
	
	@Override
	public int doStartTag() throws JspException {
		addAttribute("title", title);
		addAttribute("description", description);
		addAttribute("keywords", keywords);
		addAttribute("roles", AuthzUtil.isAdmin()? "admin": "user");
		
		WebApplicationContext app= WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		DomainService domainService = (DomainService)app.getBean("domainServiceImpl");
		
		try {
			DomainExample oe = new DomainExample();
			oe.createCriteria().andStateEqualTo((byte)1);
			oe.setOrderByClause("weight DESC");
			List<Domain> orgs = domainService.findAllWithCates(oe);
			addAttribute("orgs", orgs);
		} catch (SecurityException e) {
			e.printStackTrace();
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

}
