package com.hoti.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.CategoryExample;

public interface CategoryMapper {
	
	/**
	 * 统计
	 * @param example
	 * @return
	 */
    int countByExample(CategoryExample example);

    /**
     * 删除
     * @param example
     * @return
     */
    int deleteByExample(CategoryExample example);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(Category record);

    /**
     * 查询
     * @param example
     * @return
     */
    List<Category> selectByExample(CategoryExample example);
    
    /**
     * 查询
     * @param id
     * @return
     */
    Category selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Category record);
    
}