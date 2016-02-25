package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.dao.OrganizationMapper;
import com.kuaiba.site.db.entity.Organization;
import com.kuaiba.site.db.entity.OrganizationExample;
import com.kuaiba.site.security.LoginUser;
import com.kuaiba.site.service.OrganizationService;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.ValidUtils;
import com.kuaiba.site.vo.OrganizationVO;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Resource
	private OrganizationMapper mapper;

	@Override
	public PageInfo<Organization> findByExample(OrganizationExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
		List<Organization> list = this.findByExample(example);
		return new PageInfo<>(list);
	}

	@Override
	public int countByExample(OrganizationExample example) {
		ValidUtils.checkNotNull(example);
		return mapper.countByExample(example);
	}

	@Override
	public void deleteByExample(OrganizationExample example) {
		ValidUtils.checkNotNull(example);
		mapper.deleteByExample(example);		
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		mapper.deleteByPrimaryKey(id);		
	}

	@Override
	public void add(OrganizationVO vo) {
		ValidUtils.checkNotNull(vo);
		
		Organization record = new Organization();
		record.setCreator(LoginUser.getName());
		record.setDescription(vo.getDescription());
		record.setName(vo.getName());
		record.setState(vo.getState());
		record.setTitle(vo.getTitle());
		record.setWeight(vo.getWeight());
		
		mapper.insert(record);		
	}

	@Override
	public List<Organization> findByExample(OrganizationExample example) {
		ValidUtils.checkNotNull(example);
		return mapper.selectByExample(example);
	}

	@Override
	public Organization findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByExample(Organization record, OrganizationExample example) {
		ValidUtils.checkNotNull(record, example);
		mapper.updateByExample(record, example);
	}

	@Override
	public void updateByPrimaryKey(Long id, OrganizationVO vo) {
		ValidUtils.checkNotNull(vo);
		ValidUtils.checkPrimaryKey(id);
		
		Organization record = new Organization();
		record.setId(id);
		record.setDescription(vo.getDescription());
		record.setName(vo.getName());
		record.setState(vo.getState());
		record.setTitle(vo.getTitle());
		record.setWeight(vo.getWeight());
		
		mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Organization> findByCollect(OrganizationExample example) {
		ValidUtils.checkNotNull(example);
		return mapper.selectByCollect(example);
	}

}
