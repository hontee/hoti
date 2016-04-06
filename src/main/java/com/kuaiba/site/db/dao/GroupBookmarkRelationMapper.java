package com.kuaiba.site.db.dao;

import java.util.List;

import com.kuaiba.site.db.entity.GroupBookmarkRelation;
import com.kuaiba.site.db.entity.GroupBookmarkRelationExample;

public interface GroupBookmarkRelationMapper {
	
	/**
	 * 统计
	 * @param example
	 * @return
	 */
    int countByExample(GroupBookmarkRelationExample example);

    /**
     * 查询
     * @param example
     * @return
     */
    List<GroupBookmarkRelation> selectByExample(GroupBookmarkRelationExample example);

}