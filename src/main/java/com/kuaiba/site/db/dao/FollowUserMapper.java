package com.kuaiba.site.db.dao;

import java.util.List;

import com.kuaiba.site.db.entity.FollowUser;
import com.kuaiba.site.db.entity.FollowUserExample;

public interface FollowUserMapper {
	
	/**
	 * 统计用户关注的书签
	 * @param example
	 * @return
	 */
    int countByBookmark(FollowUserExample example);

    /**
	 * 查询用户关注的书签
	 * @param example
	 * @return
	 */
    List<FollowUser> selectByBookmark(FollowUserExample example);
    
    /**
	 * 统计用户关注的书签
	 * @param example
	 * @return
	 */
    int countByGroup(FollowUserExample example);

    /**
	 * 查询用户关注的群组
	 * @param example
	 * @return
	 */
    List<FollowUser> selectByGroup(FollowUserExample example);

}