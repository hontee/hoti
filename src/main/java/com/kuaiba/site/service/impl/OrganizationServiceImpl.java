package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.kuaiba.site.db.dao.OrganizationMapper;
import com.kuaiba.site.db.entity.Organization;
import com.kuaiba.site.db.entity.OrganizationExample;
import com.kuaiba.site.service.OrganizationService;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.ResultBuilder;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);
	
	@Resource
	private OrganizationMapper mapper;

	@Override
	public PageInfo<Organization> findByExample(OrganizationExample example, Pagination p) throws Exception {
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
		List<Organization> list = this.findByExample(example);
		return new PageInfo<>(list);
	}

	@Override
	public int countByExample(OrganizationExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public void deleteByExample(OrganizationExample example) {
		mapper.deleteByExample(example);		
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		mapper.deleteByPrimaryKey(id);		
	}

	@Override
	public void add(Organization record) {
		mapper.insert(record);		
	}

	@Override
	public List<Organization> findByExample(OrganizationExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public Organization findByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByExample(Organization record, OrganizationExample example) {
		mapper.updateByExample(record, example);
	}

	@Override
	public void updateByPrimaryKey(Organization record) {
		try {
			Preconditions.checkNotNull(record);
		} catch (Exception e) {
			logger.debug("请求参数错误.");
			ResultBuilder.failed("请求参数错误.");
		}
		mapper.updateByPrimaryKey(record);
	}

}
