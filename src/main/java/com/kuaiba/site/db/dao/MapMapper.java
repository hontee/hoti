package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MapMapper {
	
	/**
	 * 取消关注
	 * @param uid
	 * @param fid
	 * @return
	 */
	int unfollowBookmark(@Param("uid") Long uid, @Param("fid") Long fid);

	/**
	 * 添加关注
	 * @param uid
	 * @param fid
	 * @return
	 */
	int followBookmark(@Param("uid") Long uid, @Param("fid") Long fid);
	
	/**
	 * 查询用户关注的站点
	 * @param uid
	 * @return
	 */
	List<Long> selectBookmarkIdsByUserId(@Param("uid") Long uid);
	
	/**
	 * 取消关注
	 * @param uid
	 * @param fid
	 * @return
	 */
	int unfollowGroup(@Param("uid") Long uid, @Param("fid") Long fid);

	/**
	 * 关注
	 * @param uid
	 * @param fid
	 * @return
	 */
	int followGroup(@Param("uid") Long uid, @Param("fid") Long fid);
	
	/**
	 * 用户关注的主题
	 * @param uid
	 * @return
	 */
	List<Long> selectGroupIdsByUserId(@Param("uid") Long uid);
	
	/**
	 * 关注主题的用户数
	 * @param fid
	 * @return
	 */
	int countGroupUserById(@Param("fid") Long fid);
	
	
	/**
	 * 删除主题-站点
	 * @param gid
	 * @param bmid
	 * @return
	 */
    int deleteGB(@Param("gid") Long gid, @Param("bmid") Long bmid);

    /**
     * 添加主题-站点
     * @param gid
     * @param bmid
     * @return
     */
    int insertGB(@Param("gid") Long gid, @Param("bmid") Long bmid);
    
    /**
     * 统计主题-站点数
     * @param gid
     * @return
     */
    int countGBByGroupId(@Param("gid") Long gid);
}