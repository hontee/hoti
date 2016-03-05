package com.kuaiba.site.db.dao;

import org.apache.ibatis.annotations.Param;

public interface GroupBookmarkMapper {
	
    int deleteByPrimaryKey(@Param("gid") Long gid, @Param("bmid") Long bmid);

    int insert(@Param("gid") Long gid, @Param("bmid") Long bmid);

}