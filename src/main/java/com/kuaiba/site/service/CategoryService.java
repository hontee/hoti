package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.front.vo.CategoryVO;

public interface CategoryService extends Pager<Category, CategoryExample> {
	
    int countByExample(CategoryExample example) throws SecurityException;

    void deleteByExample(CategoryExample example) throws SecurityException;

    void deleteByPrimaryKey(Long id) throws SecurityException;

	void add(CategoryVO vo) throws SecurityException;

    List<Category> findByExample(CategoryExample example) throws SecurityException;

    Category findByPrimaryKey(Long id) throws SecurityException;

    void updateByExample(Category record, CategoryExample example) throws SecurityException;

    void updateByPrimaryKey(Long id, CategoryVO vo) throws SecurityException;
    
    void updateByPrimaryKey(Long id, long count) throws SecurityException;
    
    List<Category> findByOrganization(Long domain) throws SecurityException;
    
    List<Category> findByCollect(CategoryExample example) throws SecurityException;
    
    /**
	 * 验证Category名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkCategoryName(String name) throws SecurityException;
	
}
