package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.db.entity.TrackExample;

public interface TrackMapper {
	
	/**
	 * 统计
	 * @param example
	 * @return
	 */
    int countByExample(TrackExample example);

    /**
     * 删除
     * @param example
     * @return
     */
    int deleteByExample(TrackExample example);

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
    int insert(Track record);

    /**
     * 查询
     * @param example
     * @return
     */
    List<Track> selectByExample(TrackExample example);

    /**
     * 查询
     * @param id
     * @return
     */
    Track selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") Track record, @Param("example") TrackExample example);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Track record);
}