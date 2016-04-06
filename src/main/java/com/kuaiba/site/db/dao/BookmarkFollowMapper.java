package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BookmarkFollowMapper {
	
	int delete(@Param("uid") Long uid, @Param("fid") Long fid);

	int insert(@Param("uid") Long uid, @Param("fid") Long fid);
	
	/**
	 * 用户关注的书签
	 * @param uid
	 * @return
	 */
	List<Long> selectByUid(@Param("uid") Long uid);
}