package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Organization;
import com.kuaiba.site.db.entity.OrganizationExample;
import com.kuaiba.site.support.Pager;
import com.kuaiba.site.vo.OrganizationVO;

public interface OrganizationService extends Pager<Organization, OrganizationExample> {

	int countByExample(OrganizationExample example);

    void deleteByExample(OrganizationExample example);

    void deleteByPrimaryKey(Long id);

    void add(OrganizationVO vo);

    List<Organization> findByExample(OrganizationExample example);

    Organization findByPrimaryKey(Long id);
    
    List<Organization> findByCollect(OrganizationExample example);

    void updateByExample(Organization record, OrganizationExample example);

    void updateByPrimaryKey(Long id, OrganizationVO vo);
    
}
