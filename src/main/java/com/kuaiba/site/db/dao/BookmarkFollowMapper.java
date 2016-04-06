package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BookmarkFollowMapper {
	
	/**
	 * 取消关注
	 * @param uid
	 * @param fid
	 * @return
	 */
	int unfollow(@Param("uid") Long uid, @Param("fid") Long fid);

	/**
	 * 添加关注
	 * @param uid
	 * @param fid
	 * @return
	 */
	int follow(@Param("uid") Long uid, @Param("fid") Long fid);
	
	/**
	 * 查询用户关注的站点
	 * @param uid
	 * @return
	 */
	List<Long> selectByUserId(@Param("uid") Long uid);
}