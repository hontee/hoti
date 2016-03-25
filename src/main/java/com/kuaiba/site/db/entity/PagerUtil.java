package com.kuaiba.site.db.entity;

import com.github.pagehelper.PageHelper;

public class PagerUtil {
	
	/**
	 * 执行分页任务
	 * @param p
	 */
	public static void startPage(Pagination p) {
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
	}

}
