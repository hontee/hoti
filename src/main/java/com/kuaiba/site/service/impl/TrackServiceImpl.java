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
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.db.dao.TrackMapper;
import com.kuaiba.site.db.entity.VUtil;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.db.entity.TrackExample;
import com.kuaiba.site.service.TrackService;

@Service
public class TrackServiceImpl implements TrackService {
	
	@Resource
	private TrackMapper mapper;

	@Override
	public PageInfo<Track> search(TrackExample example, Pagination p) throws SecurityException { 
		try {
			VUtil.assertNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Track> list = read(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取异常失败", e);
		}
	}

	@Override
	public int count(TrackExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计异常失败", e);
		}
	}

	@Override
	public void delete(TrackExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除异常失败", e);
		}
	}

	@Override
	public void delete(Long id) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除异常失败", e);
		}
	}
	
	@Override
	public void delete(String[] ids) throws SecurityException { 
		try {
			VUtil.assertNotNull(ids);
			mapper.deleteByIds(ids);
		} catch (Exception e) {
			throw new DeleteException("删除异常失败", e);
		}
	}

	@Override
	public void add(Track record) throws SecurityException { 
		try {
			VUtil.assertNotNull(record);
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加异常失败", e);
		}
	}

	@Override
	public List<Track> read(TrackExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取异常失败", e);
		}
	}

	@Override
	public Track read(Long id) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取异常失败", e);
		}
	}

	@Override
	public void update(Track record, TrackExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新异常失败", e);
		}
	}

	@Override
	public void update(Long id, Track record) throws SecurityException { 
		try {
			VUtil.assertNotNull(record, id);
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新异常失败", e);
		}
	}

}
