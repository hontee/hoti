package com.kuaiba.site.front.taglib;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;

/**
 * Groups
 * 群组列表的视图模板
 * 在后期根据不同类型的群组开发更多的模板视图，增强页面渲染。
 * @author larry.qi
 */
public class GroupsTag extends AbstractTagSupport {

	private static final long serialVersionUID = 1L;
	private ArrayList<Object> list; // Group集合

	@Override
	public int doStartTag() throws JspException {
		addAttribute("records", list);
		super.render("groups.ftl");
		return super.doStartTag();
	}

	public ArrayList<Object> getList() {
		return list;
	}

	public void setList(ArrayList<Object> list) {
		this.list = list;
	}

}
