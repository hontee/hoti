package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.dao.ActivityMapper;
import com.kuaiba.site.db.entity.Activity;
import com.kuaiba.site.db.entity.ActivityExample;
import com.kuaiba.site.db.entity.PagerUtil;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.VUtil;
import com.kuaiba.site.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Resource
	private ActivityMapper mapper;

	@Override
	public PageInfo<Activity> find(ActivityExample example, Pagination p) throws SecurityException {
		try {
			VUtil.assertNotNull(example, p);
			PagerUtil.startPage(p);
			List<Activity> list = findAll(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取记录失败", e);
		}
	}

	@Override
	public int count(ActivityExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计记录失败", e);
		}
	}

	@Override
	public void delete(String[] ids) throws SecurityException {
		try {
			VUtil.assertNotNull(ids);
			mapper.deleteByIds(ids);
		} catch (Exception e) {
			throw new DeleteException("批量删除记录失败", e);
		}
	}

	@Override
	public void delete(Long id) throws SecurityException {
		try {
			VUtil.assertNotNull(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除记录失败", e);
		}
	}

	@Override
	public void add(Activity record) throws SecurityException {
		try {
			VUtil.assertNotNull(record);
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加记录失败", e);
		}
	}

	@Override
	public List<Activity> findAll(ActivityExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取记录失败", e);
		}
	}

	@Override
	public Activity findOne(Long id) throws SecurityException {
		try {
			VUtil.assertNotNull(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取记录失败", e);
		}
	}

}
