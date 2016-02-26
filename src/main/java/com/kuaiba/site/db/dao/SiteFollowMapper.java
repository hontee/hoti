package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SiteFollowMapper {
	
	int deleteByPrimaryKey(@Param("uid") Long uid, @Param("fid") Long fid);

	int insert(@Param("uid") Long uid, @Param("fid") Long fid);
	
	List<Long> selectByUid(@Param("uid") Long uid);
	
}