package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.support.Pager;

public interface RecommendService extends Pager<Recommend, RecommendExample> {
	
	int countByExample(RecommendExample example);

    void deleteByExample(RecommendExample example);

    void deleteByPrimaryKey(Long id);

    void add(Recommend record);

    List<Recommend> findByExample(RecommendExample example);

    Recommend findByPrimaryKey(Long id);

    void updateByExample(Recommend record, RecommendExample example);

    void updateByPrimaryKey(Recommend record);

}
