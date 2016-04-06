package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface GroupFollowMapper {
	
	int delete(@Param("uid") Long uid, @Param("fid") Long fid);

	int insert(@Param("uid") Long uid, @Param("fid") Long fid);
	
	/**
	 * 用户关注的主题
	 * @param uid
	 * @return
	 */
	List<Long> selectByUid(@Param("uid") Long uid);
	
	/**
	 * 统计Group关注的用户数
	 * @param fid
	 * @return
	 */
	int countByFid(@Param("fid") Long fid);
}