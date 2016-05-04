package com.ikyer.site.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;

/**
 * 定义EasyUI的DataGrid数据类型
 * @author larry.qi
 *
 */
public class DataGrid<T> implements Serializable {
	
	private static final long serialVersionUID = 6855401753433772729L;

	private long total;
	private List<T> rows;

	public DataGrid(PageInfo<T> p) {
		Preconditions.checkNotNull(p, "获取数据失败.");
		this.total = p.getTotal();
		this.rows = p.getList();
	}

	public DataGrid() {
		super();
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		if (rows == null) {
			rows = new ArrayList<T>();
		}
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
