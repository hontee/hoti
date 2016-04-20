package com.hoti.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hoti.site.db.entity.Recommend;
import com.hoti.site.db.entity.RecommendExample;

public interface RecommendMapper {

  int countByExample(RecommendExample example);

  int deleteByExample(RecommendExample example);

  int deleteByPrimaryKey(Long id);

  int insert(Recommend record);

  List<Recommend> selectByExample(RecommendExample example);

  Recommend selectByPrimaryKey(Long id);

  int updateByExample(@Param("record") Recommend record,
      @Param("example") RecommendExample example);

  int updateByPrimaryKey(Recommend record);
}
