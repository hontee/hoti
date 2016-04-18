package com.hoti.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hoti.site.db.entity.Recommend;
import com.hoti.site.db.entity.RecommendExample;

public interface RecommendMapper {
	
	/**
	 * 统计
	 * @param example
	 * @return
	 */
    int countByExample(RecommendExample example);

    /**
     * 删除
     * @param example
     * @return
     */
    int deleteByExample(RecommendExample example);

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
    int insert(Recommend record);

    /**
     * 查询
     * @param example
     * @return
     */
    List<Recommend> selectByExample(RecommendExample example);

    /**
     * 查询
     * @param id
     * @return
     */
    Recommend selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") Recommend record, @Param("example") RecommendExample example);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Recommend record);
    
}