package com.kuaiba.site.db.dao;

import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecommendMapper {
	
    int countByExample(RecommendExample example);

    int deleteByExample(RecommendExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Recommend record);

    List<Recommend> selectByExample(RecommendExample example);

    Recommend selectByPrimaryKey(Long id);

    int updateByExample(@Param("record") Recommend record, @Param("example") RecommendExample example);

    int updateByPrimaryKey(Recommend record);
    
}