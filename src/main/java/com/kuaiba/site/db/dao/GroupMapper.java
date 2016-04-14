package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;

public interface GroupMapper {
	
	/**
	 * 统计
	 * @param example
	 * @return
	 */
    int countByExample(GroupExample example);

    /**
     * 删除
     * @param example
     * @return
     */
    int deleteByExample(GroupExample example);

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
    int insert(Group record);

    /**
     * 查询
     * @param example
     * @return
     */
    List<Group> selectByExample(GroupExample example);

    /**
     * 查询
     * @param id
     * @return
     */
    Group selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Group record);
    
}