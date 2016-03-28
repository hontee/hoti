package com.kuaiba.site.front.taglib;

import javax.servlet.jsp.JspException;

import com.kuaiba.site.db.entity.Group;

/**
 * Group
 * 群组封面的视图模板
 * 在后期根据不同类型的群组开发更多的模板视图，增强页面渲染。
 * @author larry.qi
 */
public class GroupTag extends AbstractTagSupport {

	private static final long serialVersionUID = 1L;
	private Group record; // 群组记录

	@Override
	public int doStartTag() throws JspException {
		addAttribute("record", record);
		super.render("group.ftl");
		return super.doStartTag();
	}

	public Group getRecord() {
		return record;
	}

	public void setRecord(Group record) {
		this.record = record;
	}

}
