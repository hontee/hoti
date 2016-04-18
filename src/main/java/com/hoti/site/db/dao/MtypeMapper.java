package com.hoti.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hoti.site.db.entity.Mtype;
import com.hoti.site.db.entity.MtypeExample;

public interface MtypeMapper {
	
	/**
	 * 统计
	 * @param example
	 * @return
	 */
    int countByExample(MtypeExample example);

    /**
     * 删除
     * @param example
     * @return
     */
    int deleteByExample(MtypeExample example);

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
    int insert(Mtype record);

    /**
     * 查询
     * @param example
     * @return
     */
    List<Mtype> selectByExample(MtypeExample example);

    /**
     * 查询
     * @param id
     * @return
     */
    Mtype selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") Mtype record, @Param("example") MtypeExample example);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Mtype record);
    
}