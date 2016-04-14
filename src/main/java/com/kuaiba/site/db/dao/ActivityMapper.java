package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kuaiba.site.db.entity.Activity;
import com.kuaiba.site.db.entity.ActivityExample;

public interface ActivityMapper {
	
	/**
	 * 统计
	 * @param example
	 * @return
	 */
    int countByExample(ActivityExample example);

    /**
     * 删除
     * @param example
     * @return
     */
    int deleteByExample(ActivityExample example);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);
    
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(String[] ids);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(Activity record);

    /**
     * 查询
     * @param example
     * @return
     */
    List<Activity> selectByExample(ActivityExample example);

    /**
     * 查询ID
     * @param id
     * @return
     */
    Activity selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Activity record);
}