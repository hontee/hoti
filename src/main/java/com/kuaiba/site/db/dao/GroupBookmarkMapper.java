package com.kuaiba.site.db.dao;

import org.apache.ibatis.annotations.Param;

public interface GroupBookmarkMapper {
	
	/**
	 * 删除站点
	 * @param gid
	 * @param bmid
	 * @return
	 */
    int delete(@Param("gid") Long gid, @Param("bmid") Long bmid);

    /**
     * 添加站点
     * @param gid
     * @param bmid
     * @return
     */
    int insert(@Param("gid") Long gid, @Param("bmid") Long bmid);
    
    /**
     * 统计站点数
     * @param gid
     * @return
     */
    int countByGroupId(@Param("gid") Long gid);

}