package com.kuaiba.site.front.taglib;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;

/**
 * BookMarks视图模板
 * 在后期的开发中可根据业务需求开发更多实用的展示模板
 * @author larry.qi
 */
public class BookmarksTag extends AbstractTagSupport {

	private static final long serialVersionUID = 1L;
	private ArrayList<Object> list;

	@Override
	public int doStartTag() throws JspException {
		addAttribute("records", list);
		super.render("bookmarks.ftl");
		return super.doStartTag();
	}

	public ArrayList<Object> getList() {
		return list;
	}

	public void setList(ArrayList<Object> list) {
		this.list = list;
	}

}
