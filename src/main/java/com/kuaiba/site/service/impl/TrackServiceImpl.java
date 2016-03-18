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
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.db.entity.TrackExample;
import com.kuaiba.site.service.TrackService;

@Service
public class TrackServiceImpl implements TrackService {
	
	@Resource
	private TrackMapper mapper;

	@Override
	public PageInfo<Track> findByExample(TrackExample example, Pagination p) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Track> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取异常失败", e);
		}
	}

	@Override
	public int countByExample(TrackExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计异常失败", e);
		}
	}

	@Override
	public void deleteByExample(TrackExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除异常失败", e);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除异常失败", e);
		}
	}
	
	@Override
	public void deleteByPrimaryKey(String[] ids) throws SecurityException { 
		try {
			ContraintValidator.checkArrays(ids);
			mapper.deleteByIds(ids);
		} catch (Exception e) {
			throw new DeleteException("删除异常失败", e);
		}
	}

	@Override
	public void add(Track record) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record);
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加异常失败", e);
		}
	}

	@Override
	public List<Track> findByExample(TrackExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取异常失败", e);
		}
	}

	@Override
	public Track findByPrimaryKey(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取异常失败", e);
		}
	}

	@Override
	public void updateByExample(Track record, TrackExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新异常失败", e);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, Track record) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record);
			ContraintValidator.checkPrimaryKey(id);
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新异常失败", e);
		}
	}

}
