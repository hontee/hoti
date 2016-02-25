package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.support.Pager;
import com.kuaiba.site.vo.CategoryVO;

public interface CategoryService extends Pager<Category, CategoryExample> {
	
    int countByExample(CategoryExample example);

    void deleteByExample(CategoryExample example);

    void deleteByPrimaryKey(Long id);

	void add(CategoryVO vo);

    List<Category> findByExample(CategoryExample example);

    Category findByPrimaryKey(Long id);

    void updateByExample(Category record, CategoryExample example);

    void updateByPrimaryKey(Long id, CategoryVO vo);
    
    List<Category> findByOrganization(Long organization);
	
}
