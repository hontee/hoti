package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.db.dao.DomainMapper;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.exceptions.BType;
import com.kuaiba.site.exceptions.BusinessException;
import com.kuaiba.site.front.vo.DomainVO;
import com.kuaiba.site.security.Administrator;
import com.kuaiba.site.service.DomainService;
import com.kuaiba.site.service.kit.Pagination;
import com.kuaiba.site.service.kit.ValidKit;

@Service
public class DomainServiceImpl implements DomainService {
	
	private Logger logger = LoggerFactory.getLogger(DomainServiceImpl.class);
	
	@Resource
	private DomainMapper mapper;

	@Override
	public PageInfo<Domain> findByExample(DomainExample example, Pagination p) {
		ValidKit.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Domain> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public int countByExample(DomainExample example) {
		ValidKit.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void deleteByExample(DomainExample example) {
		ValidKit.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}		
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidKit.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void add(DomainVO vo) {
		ValidKit.checkNotNull(vo);
		try {
			Domain record = new Domain();
			record.setCreator(Administrator.getName());
			record.setDescription(vo.getDescription());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setWeight(vo.getWeight());
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}		
	}

	@Override
	public List<Domain> findByExample(DomainExample example) {
		ValidKit.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public Domain findByPrimaryKey(Long id) {
		ValidKit.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void updateByExample(Domain record, DomainExample example) {
		ValidKit.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, DomainVO vo) {
		ValidKit.checkNotNull(vo);
		ValidKit.checkPrimaryKey(id);
		try {
			Domain record = new Domain();
			record.setId(id);
			record.setDescription(vo.getDescription());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setWeight(vo.getWeight());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public List<Domain> findByCollect(DomainExample example) {
		ValidKit.checkNotNull(example);
		try {
			return mapper.selectByCollect(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

}
