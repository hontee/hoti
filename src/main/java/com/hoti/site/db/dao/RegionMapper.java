package com.hoti.site.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.hoti.site.db.entity.Region;
import com.hoti.site.db.entity.RegionExample;

public interface RegionMapper {

  int countByExample(RegionExample example);

  int deleteByExample(RegionExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Region record);

  List<Region> selectByExample(RegionExample example);

  Region selectByPrimaryKey(Integer id);

  int updateByExample(@Param("record") Region record, @Param("example") RegionExample example);

  int updateByPrimaryKey(Region record);
}
