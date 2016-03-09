package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.core.exceptions.ExceptionIds;
import com.kuaiba.site.core.exceptions.LogicException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.dao.MtypeMapper;
import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import com.kuaiba.site.front.vo.MtypeVO;
import com.kuaiba.site.service.MtypeService;
import com.kuaiba.site.service.utils.Pagination;
import com.kuaiba.site.service.utils.ValidUtils;

@Service
public class MtypeServiceImpl implements MtypeService {
	
	private Logger logger = LoggerFactory.getLogger(MtypeServiceImpl.class);
	
	@Resource
	private MtypeMapper mapper;

	@Override
	public PageInfo<Mtype> findByExample(MtypeExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Mtype> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public int countByExample(MtypeExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void deleteByExample(MtypeExample example) {
		ValidUtils.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void add(MtypeVO vo) {
		ValidUtils.checkNotNull(vo);
		try {
			Mtype record = new Mtype();
			record.setWeight(vo.getWeight());
			record.setCreator(CurrentUser.getCurrentUserName());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setName(vo.getName());
			record.setTitle(vo.getTitle());
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_ADD);
		}
	}

	@Override
	public List<Mtype> findByExample(MtypeExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public Mtype findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void updateByExample(Mtype record, MtypeExample example) {
		ValidUtils.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, MtypeVO vo) {
		ValidUtils.checkNotNull(vo);
		ValidUtils.checkPrimaryKey(id);
		try {
			Mtype record = new Mtype();
			record.setId(id);
			record.setWeight(vo.getWeight());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setName(vo.getName());
			record.setTitle(vo.getTitle());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}
	
	@Override
	public boolean checkMTypeName(String name) {
		ValidUtils.checkNotNull(name);
		try {
			MtypeExample example = new MtypeExample();
			example.createCriteria().andNameEqualTo(name);
			List<Mtype> list = mapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkMTypeTitle(String title) {
		ValidUtils.checkNotNull(title);
		try {
			MtypeExample example = new MtypeExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Mtype> list = mapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}
	
}
