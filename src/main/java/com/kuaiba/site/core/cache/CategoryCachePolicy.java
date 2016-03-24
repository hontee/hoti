package com.kuaiba.site.core.cache;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Category;

/**
 * 分类缓存策略
 * @author larry.qi
 *
 */
public interface CategoryCachePolicy {
	
	/**
	 * 获取所有分类
	 * @return
	 * @throws SecurityException
	 */
	public List<Category> getCategories() throws SecurityException;

}
