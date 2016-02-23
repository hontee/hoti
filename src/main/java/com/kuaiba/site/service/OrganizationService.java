package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Organization;
import com.kuaiba.site.db.entity.OrganizationExample;
import com.kuaiba.site.support.Pager;

public interface OrganizationService extends Pager<Organization, OrganizationExample> {

	int countByExample(OrganizationExample example);

    void deleteByExample(OrganizationExample example);

    void deleteByPrimaryKey(Long id);

    void add(Organization record);

    List<Organization> findByExample(OrganizationExample example);

    Organization findByPrimaryKey(Long id);

    void updateByExample(Organization record, OrganizationExample example);

    void updateByPrimaryKey(Organization record);
    
}
