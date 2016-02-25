package com.kuaiba.site.db.dao;

import com.kuaiba.site.db.entity.Organization;
import com.kuaiba.site.db.entity.OrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrganizationMapper {
	
    int countByExample(OrganizationExample example);

    int deleteByExample(OrganizationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Organization record);

    List<Organization> selectByExample(OrganizationExample example);

    Organization selectByPrimaryKey(Long id);
    
    List<Organization> selectByCollect(OrganizationExample example);
    
    int updateByExample(@Param("record") Organization record, @Param("example") OrganizationExample example);

    int updateByPrimaryKey(Organization record);
    
}