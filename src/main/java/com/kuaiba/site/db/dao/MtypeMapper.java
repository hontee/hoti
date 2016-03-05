package com.kuaiba.site.db.dao;

import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MtypeMapper {
	
    int countByExample(MtypeExample example);

    int deleteByExample(MtypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Mtype record);

    List<Mtype> selectByExample(MtypeExample example);

    Mtype selectByPrimaryKey(Long id);

    int updateByExample(@Param("record") Mtype record, @Param("example") MtypeExample example);

    int updateByPrimaryKey(Mtype record);
    
}