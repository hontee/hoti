package com.hoti.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hoti.site.db.entity.Domain;
import com.hoti.site.db.entity.DomainExample;

public interface DomainMapper {
	
	/**
	 * 统计
	 * @param example
	 * @return
	 */
    int countByExample(DomainExample example);

    /**
     * 删除
     * @param example
     * @return
     */
    int deleteByExample(DomainExample example);

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
    int insert(Domain record);

    /**
     * 查询
     * @param example
     * @return
     */
    List<Domain> selectByExample(DomainExample example);
    
    /**
     * 查询
     * @param id
     * @return
     */
    Domain selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") Domain record, @Param("example") DomainExample example);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Domain record);
}