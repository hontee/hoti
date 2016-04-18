package com.hoti.site.service;

import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.Group;

/**
 * 数据统计
 * @author larry.qi
 *
 */
public interface Countable {
	
	/**
	 * 统计 {@link Category}
	 */
	public void countCategoryTask() throws SecurityException;
	
	/**
	 * 统计 {@link Group}
	 */
	public void countGroupTask() throws SecurityException;
	
}
