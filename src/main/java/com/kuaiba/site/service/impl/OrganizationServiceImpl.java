package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.db.dao.OrganizationMapper;
import com.kuaiba.site.db.entity.Organization;
import com.kuaiba.site.db.entity.OrganizationExample;
import com.kuaiba.site.exceptions.BusinessException;
import com.kuaiba.site.front.vo.OrganizationVO;
import com.kuaiba.site.security.LoginUser;
import com.kuaiba.site.service.OrganizationService;
import com.kuaiba.site.service.kit.Pagination;
import com.kuaiba.site.service.kit.ValidKit;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	private Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);
	
	@Resource
	private OrganizationMapper mapper;

	@Override
	public PageInfo<Organization> findByExample(OrganizationExample example, Pagination p) {
		ValidKit.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Organization> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public int countByExample(OrganizationExample example) {
		ValidKit.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public void deleteByExample(OrganizationExample example) {
		ValidKit.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}		
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidKit.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public void add(OrganizationVO vo) {
		ValidKit.checkNotNull(vo);
		try {
			Organization record = new Organization();
			record.setCreator(LoginUser.getName());
			record.setDescription(vo.getDescription());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setWeight(vo.getWeight());
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}		
	}

	@Override
	public List<Organization> findByExample(OrganizationExample example) {
		ValidKit.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public Organization findByPrimaryKey(Long id) {
		ValidKit.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateByExample(Organization record, OrganizationExample example) {
		ValidKit.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, OrganizationVO vo) {
		ValidKit.checkNotNull(vo);
		ValidKit.checkPrimaryKey(id);
		try {
			Organization record = new Organization();
			record.setId(id);
			record.setDescription(vo.getDescription());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setWeight(vo.getWeight());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Organization> findByCollect(OrganizationExample example) {
		ValidKit.checkNotNull(example);
		try {
			return mapper.selectByCollect(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

}
