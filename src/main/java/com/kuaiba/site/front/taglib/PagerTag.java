package com.kuaiba.site.front.taglib;

import javax.servlet.jsp.JspException;

import com.kuaiba.site.db.entity.PagerUtil;

/**
 * 分页模板
 * @author larry.qi
 */
public class PagerTag extends AbstractTagSupport {
	
	private static final long serialVersionUID = 1L;
	private PagerUtil page;  // 分页
	
	@Override
	public int doStartTag() throws JspException {
		addAttribute("page", page);
		super.render("pager.ftl");
		return super.doStartTag();
	}

	public PagerUtil getPage() {
		return page;
	}

	public void setPage(PagerUtil page) {
		this.page = page;
	}

}
