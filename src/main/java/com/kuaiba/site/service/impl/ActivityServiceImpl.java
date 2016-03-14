package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.core.exceptions.CheckedException;
import com.kuaiba.site.core.exceptions.ExceptionIds;
import com.kuaiba.site.core.exceptions.LogicException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.dao.ActivityMapper;
import com.kuaiba.site.db.entity.Activity;
import com.kuaiba.site.db.entity.ActivityExample;
import com.kuaiba.site.service.ActivityService;
import com.kuaiba.site.service.utils.IPUtils;
import com.kuaiba.site.service.utils.LogUtils;
import com.kuaiba.site.service.utils.Pagination;
import com.kuaiba.site.service.utils.ValidUtils;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	private Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);
	
	@Resource
	private ActivityMapper mapper;

	@Override
	public PageInfo<Activity> findByExample(ActivityExample example, Pagination p) {
		try {
			ValidUtils.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Activity> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public int countByExample(ActivityExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void deleteByExample(ActivityExample example) {
		ValidUtils.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void add(Activity record) {
		ValidUtils.checkNotNull(record);
		
		try {
			mapper.insert(record);
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_ADD);
		}
	}

	@Override
	public void logger(String action, String tbl, String desc, HttpServletRequest request) {
		try {
			Activity record = new Activity();
			record.setCreator(CurrentUser.getCurrentUserName());
			record.setUserType(CurrentUser.isAdmin()? "admin": "user");
			record.setDescription(desc);
			record.setIpAddr(IPUtils.getIpAddr(request));
			record.setName(action);
			record.setTbl(tbl);
			this.add(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
		}
	}

	@Override
	public List<Activity> findByExample(ActivityExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public Activity findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

}
