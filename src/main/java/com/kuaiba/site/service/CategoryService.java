package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.front.vo.CategoryVO;
import com.kuaiba.site.service.utils.Pager;

public interface CategoryService extends Pager<Category, CategoryExample> {
	
    int countByExample(CategoryExample example);

    void deleteByExample(CategoryExample example);

    void deleteByPrimaryKey(Long id);

	void add(CategoryVO vo);

    List<Category> findByExample(CategoryExample example);

    Category findByPrimaryKey(Long id);

    void updateByExample(Category record, CategoryExample example);

    void updateByPrimaryKey(Long id, CategoryVO vo);
    
    List<Category> findByOrganization(Long domain);
    
    List<Category> findByCollect(CategoryExample example);
    
    /**
	 * 验证Category名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkCategoryName(String name);
	
}
