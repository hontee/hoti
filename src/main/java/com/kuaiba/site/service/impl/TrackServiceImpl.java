package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.core.exceptions.BType;
import com.kuaiba.site.core.exceptions.BusinessException;
import com.kuaiba.site.db.dao.TrackMapper;
import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.db.entity.TrackExample;
import com.kuaiba.site.service.TrackService;
import com.kuaiba.site.service.utils.Pagination;
import com.kuaiba.site.service.utils.ValidUtils;

@Service
public class TrackServiceImpl implements TrackService {
	
	private Logger logger = LoggerFactory.getLogger(TrackServiceImpl.class);
	
	@Resource
	private TrackMapper mapper;

	@Override
	public PageInfo<Track> findByExample(TrackExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Track> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.BUSINESS_ERROR);
		}
	}

	@Override
	public int countByExample(TrackExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.BUSINESS_ERROR);
		}
	}

	@Override
	public void deleteByExample(TrackExample example) {
		ValidUtils.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.BUSINESS_ERROR);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.BUSINESS_ERROR);
		}
	}

	@Override
	public void add(Track record) {
		ValidUtils.checkNotNull(record);
		try {
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.BUSINESS_ERROR);
		}
	}

	@Override
	public List<Track> findByExample(TrackExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.BUSINESS_ERROR);
		}
	}

	@Override
	public Track findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.BUSINESS_ERROR);
		}
	}

	@Override
	public void updateByExample(Track record, TrackExample example) {
		ValidUtils.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.BUSINESS_ERROR);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, Track record) {
		ValidUtils.checkNotNull(record);
		ValidUtils.checkPrimaryKey(id);
		try {
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.BUSINESS_ERROR);
		}
	}

}
