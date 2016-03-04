package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;

public interface DomainMapper {
	
    int countByExample(DomainExample example);

    int deleteByExample(DomainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Domain record);

    List<Domain> selectByExample(DomainExample example);
    
    List<Domain> selectByCollect(DomainExample example);

    Domain selectByPrimaryKey(Long id);

    int updateByExample(@Param("record") Domain record, @Param("example") DomainExample example);

    int updateByPrimaryKey(Domain record);
}