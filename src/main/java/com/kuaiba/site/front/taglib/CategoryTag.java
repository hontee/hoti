package com.kuaiba.site.front.taglib;

import javax.servlet.jsp.JspException;

import com.kuaiba.site.db.entity.Category;

/**
 * Category
 * 分类封面的视图模板
 * 在后期根据不同类型的群组开发更多的模板视图，增强页面渲染。
 * @author larry.qi
 */
public class CategoryTag extends AbstractTagSupport {

	private static final long serialVersionUID = 1L;
	private Category record; // 分类记录
	private String f;

	@Override
	public int doStartTag() throws JspException {
		addAttribute("record", record);
		addAttribute("f", f);
		super.render("category.ftl");
		return super.doStartTag();
	}

	public Category getRecord() {
		return record;
	}

	public void setRecord(Category record) {
		this.record = record;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

}
