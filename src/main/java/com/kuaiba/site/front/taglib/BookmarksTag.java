package com.kuaiba.site.front.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;

import com.kuaiba.site.db.entity.Bookmark;

/**
 * BookMarks视图模板
 * 在后期的开发中可根据业务需求开发更多实用的展示模板
 * @author larry.qi
 */
public class BookmarksTag extends AbstractTagSupport {

	private static final long serialVersionUID = 1L;
	private List<Bookmark> list; // 书签列表

	@Override
	public int doStartTag() throws JspException {
		addAttribute("records", list);
		super.render("bookmarks.ftl");
		return super.doStartTag();
	}

	public List<Bookmark> getList() {
		return list;
	}

	public void setList(List<Bookmark> list) {
		this.list = list;
	}

}
