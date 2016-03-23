package com.kuaiba.site.db.dao;

import java.util.List;

import com.kuaiba.site.db.entity.FollowUser;
import com.kuaiba.site.db.entity.FollowUserExample;

public interface FollowUserMapper {
	
    int countBookmarkByExample(FollowUserExample example);

    List<FollowUser> selectBookmarkByExample(FollowUserExample example);
    
    int countGroupByExample(FollowUserExample example);

    List<FollowUser> selectGroupByExample(FollowUserExample example);

}