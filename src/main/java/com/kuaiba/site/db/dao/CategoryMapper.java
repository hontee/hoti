package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;

public interface CategoryMapper {
	
    int countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Long id);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKey(Category record);
    
    List<Category> selectByOrganization(@Param("organization") Long organization);
    
    List<Category> selectByCollect(CategoryExample example);
    
}