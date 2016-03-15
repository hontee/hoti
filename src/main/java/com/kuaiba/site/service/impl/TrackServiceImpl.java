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
import com.kuaiba.site.db.dao.TrackMapper;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.db.entity.TrackExample;
import com.kuaiba.site.service.TrackService;

@Service
public class TrackServiceImpl implements TrackService {
	
	private Logger logger = LoggerFactory.getLogger(TrackServiceImpl.class);
	
	@Resource
	private TrackMapper mapper;

	@Override
	public PageInfo<Track> findByExample(TrackExample example, Pagination p) {
		ContraintValidator.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Track> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public int countByExample(TrackExample example) {
		ContraintValidator.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void deleteByExample(TrackExample example) {
		ContraintValidator.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ContraintValidator.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void add(Track record) {
		ContraintValidator.checkNotNull(record);
		try {
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_ADD);
		}
	}

	@Override
	public List<Track> findByExample(TrackExample example) {
		ContraintValidator.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public Track findByPrimaryKey(Long id) {
		ContraintValidator.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void updateByExample(Track record, TrackExample example) {
		ContraintValidator.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, Track record) {
		ContraintValidator.checkNotNull(record);
		ContraintValidator.checkPrimaryKey(id);
		try {
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

}
