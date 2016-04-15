package com.kuaiba.site.db.dao;

import com.kuaiba.site.db.entity.Region;
import com.kuaiba.site.db.entity.RegionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
