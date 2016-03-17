package com.kuaiba.site.db.entity;

import com.google.common.base.CaseFormat;

/**
 * 分页组件
 * @author larry.qi
 */
public class Pagination {

	public static final int PAGE = 1;
	public static final int ROWS = 20;
	public static final String SORT = "id";
	public static final String ORDER = "DESC";
	public static final Pagination DEFAULT = new Pagination();

	/**
	 * 当前页
	 */
	private int page;
	
	/**
	 * 每页显示数
	 */
	private int rows;
	
	/**
	 * 排序字段
	 */
	private String sort;
	
	/**
	 * 排序方式：asc, desc
	 */
	private String order;

	public Pagination() {
		this.page = PAGE;
		this.rows = ROWS;
		this.sort = SORT;
		this.order = ORDER;
	}

	public Pagination(int page, int rows) {
		this.page = page;
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	/**
	 * 排序
	 * @return
	 */
	public String getOrderByClause() {
		// 变量名称转换
		String sort = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.sort);
		return sort + " " + this.order;
	}
	
}
