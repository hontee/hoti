package com.hoti.site.db.entity;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.SecurityException;

public interface Pager<T, S> {
	
	/**
	 * * 带分页的条件查询
	 * @param example
	 * @param p
	 * @return PageInfo
	 * @throws SecurityException
	 */
	PageInfo<T> find(S example, Pagination p) throws SecurityException;

}
