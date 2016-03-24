package com.kuaiba.site.db.dao;

import org.apache.ibatis.annotations.Param;

public interface GroupFollowMapper {
	
	int delete(@Param("uid") Long uid, @Param("fid") Long fid);

	int insert(@Param("uid") Long uid, @Param("fid") Long fid);
	
	/**
	 * 统计Group关注的用户数
	 * @param fid
	 * @return
	 */
	int countByFid(@Param("fid") Long fid);
}