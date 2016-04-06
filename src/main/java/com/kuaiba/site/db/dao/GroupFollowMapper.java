package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface GroupFollowMapper {
	
	/**
	 * 取消关注
	 * @param uid
	 * @param fid
	 * @return
	 */
	int unfollow(@Param("uid") Long uid, @Param("fid") Long fid);

	/**
	 * 关注
	 * @param uid
	 * @param fid
	 * @return
	 */
	int follow(@Param("uid") Long uid, @Param("fid") Long fid);
	
	/**
	 * 用户关注的主题
	 * @param uid
	 * @return
	 */
	List<Long> selectByUserId(@Param("uid") Long uid);
	
	/**
	 * 关注的用户数
	 * @param fid
	 * @return
	 */
	int countByGroupId(@Param("fid") Long fid);
}