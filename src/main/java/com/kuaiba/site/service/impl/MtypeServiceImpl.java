package com.kuaiba.site.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.cache.CacheIDs;
import com.kuaiba.site.core.cache.Memcacheds;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.dao.MtypeMapper;
import com.kuaiba.site.db.entity.Attribute;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.front.vo.MtypeVO;
import com.kuaiba.site.service.MtypeService;

@Service
public class MtypeServiceImpl implements MtypeService {
	
	@Resource
	private MtypeMapper mapper;

	@Override
	public PageInfo<Mtype> search(MtypeExample example, Pagination p) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Mtype> list = read(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取类型失败", e);
		}
	}

	@Override
	public int count(MtypeExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计类型失败", e);
		}
	}

	@Override
	public void delete(MtypeExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除类型失败", e);
		}
	}

	@Override
	public void delete(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除类型失败", e);
		}
	}

	@Override
	public void add(MtypeVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			Mtype record = new Mtype();
			record.setWeight(vo.getWeight());
			record.setCreator(CurrentUser.getCurrentUserName());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setName(vo.getName());
			record.setTitle(vo.getTitle());
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加类型失败", e);
		}
	}

	@Override
	public List<Mtype> read(MtypeExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取类型失败", e);
		}
	}

	@Override
	public Mtype read(Long id) throws SecurityException {
		
		ContraintValidator.checkPrimaryKey(id);
		List<Mtype> list = this.getMtypes();
		
		for (Mtype mtype : list) {
			if (mtype.getId().equals(id)) {
				return mtype;
			}
		}

		return new Mtype();
	}

	@Override
	public void update(Mtype record, MtypeExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新类型失败", e);
		}
	}

	@Override
	public void update(Long id, MtypeVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			ContraintValidator.checkPrimaryKey(id);
			Mtype record = new Mtype();
			record.setId(id);
			record.setWeight(vo.getWeight());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setName(vo.getName());
			record.setTitle(vo.getTitle());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新类型失败", e);
		}
	}
	
	
	@Override
	public boolean validate(Attribute attr, String value) throws SecurityException {
		ContraintValidator.checkNotNull(value);
		MtypeExample example = new MtypeExample();
		
		if (attr == Attribute.TITLE) {
			example.createCriteria().andTitleEqualTo(value);
		} else { // Attribute.NAME
			example.createCriteria().andNameEqualTo(value);
		}
		
		return !mapper.selectByExample(example).isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mtype> getMtypes() throws SecurityException {
		
		List<Mtype> list = new ArrayList<>();
		
		if (Memcacheds.exists(CacheIDs.MTYPES)) {
			list = (List<Mtype>) Memcacheds.get(CacheIDs.MTYPES);
		} else {
			// 从数据库中获取
			list = read(new MtypeExample());
			Memcacheds.set(CacheIDs.MTYPES, 1000 * 60 * 30, list);
		}
		
		return list;
	}
	
}
