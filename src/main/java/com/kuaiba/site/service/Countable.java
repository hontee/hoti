package com.kuaiba.site.service;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.Group;

/**
 * 数据统计
 * @author larry.qi
 *
 */
public interface Countable {
	
	/**
	 * 统计 {@link Domain}
	 */
	public void countDomainTask() throws SecurityException;
	
	/**
	 * 统计 {@link Category}
	 */
	public void countCategoryTask() throws SecurityException;
	
	/**
	 * 统计 {@link Group}
	 */
	public void countGroupTask() throws SecurityException;
	
}
