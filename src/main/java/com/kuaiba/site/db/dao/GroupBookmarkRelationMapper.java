package com.kuaiba.site.db.dao;

import java.util.List;

import com.kuaiba.site.db.entity.GroupBookmarkRelation;
import com.kuaiba.site.db.entity.GroupBookmarkRelationExample;

public interface GroupBookmarkRelationMapper {
	
    int countByExample(GroupBookmarkRelationExample example);

    List<GroupBookmarkRelation> selectByExample(GroupBookmarkRelationExample example);

}