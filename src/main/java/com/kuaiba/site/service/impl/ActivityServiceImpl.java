package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.dao.ActivityMapper;
import com.kuaiba.site.db.entity.Activity;
import com.kuaiba.site.db.entity.ActivityExample;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Resource
	private ActivityMapper mapper;

	@Override
	public PageInfo<Activity> findByExample(ActivityExample example, Pagination p) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Activity> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取记录失败", e);
		}
	}

	@Override
	public int countByExample(ActivityExample example) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计记录失败", e);
		}
	}

	@Override
	public void deleteByPrimaryKey(String[] ids) throws SecurityException {
		try {
			ContraintValidator.checkArrays(ids);
			mapper.deleteByIds(ids);
		} catch (Exception e) {
			throw new DeleteException("批量删除记录失败", e);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) throws SecurityException {
		try {
			ContraintValidator.checkPrimaryKey(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除记录失败", e);
		}
	}

	@Override
	public void add(Activity record) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(record);
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加记录失败", e);
		}
	}

	@Override
	public List<Activity> findByExample(ActivityExample example) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取记录失败", e);
		}
	}

	@Override
	public Activity findByPrimaryKey(Long id) throws SecurityException {
		try {
			ContraintValidator.checkPrimaryKey(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取记录失败", e);
		}
	}

}
