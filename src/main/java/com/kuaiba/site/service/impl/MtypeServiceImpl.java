package com.kuaiba.site.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.cache.CacheIDs;
import com.kuaiba.site.core.cache.MemcachedUtil;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.dao.MtypeMapper;
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
	public PageInfo<Mtype> findByExample(MtypeExample example, Pagination p) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Mtype> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取类型失败", e);
		}
	}

	@Override
	public int countByExample(MtypeExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计类型失败", e);
		}
	}

	@Override
	public void deleteByExample(MtypeExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除类型失败", e);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) throws SecurityException { 
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
	public List<Mtype> findByExample(MtypeExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取类型失败", e);
		}
	}

	@Override
	public Mtype findByPrimaryKey(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取类型失败", e);
		}
	}

	@Override
	public Mtype findByCache(Long id) throws SecurityException {
		Mtype mt = new Mtype();
		try {
			ContraintValidator.checkPrimaryKey(id);
			List<Mtype> list = this.findAllByCache();
			for (Mtype mtype : list) {
				if (id == mtype.getId()) {
					mt = mtype;
					break;
				}
			}
		} catch (Exception e) {
			throw new ReadException("读取类型失败", e);
		}
		
		return mt;
	}

	@Override
	public void updateByExample(Mtype record, MtypeExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新类型失败", e);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, MtypeVO vo) throws SecurityException { 
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
	public boolean checkMTypeName(String name) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(name);
			MtypeExample example = new MtypeExample();
			example.createCriteria().andNameEqualTo(name);
			List<Mtype> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
			throw new ReadException("检测类型名称失败", e);
		}
	}

	@Override
	public boolean checkMTypeTitle(String title) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(title);
			MtypeExample example = new MtypeExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Mtype> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
			throw new ReadException("检测类型标题失败", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mtype> findAllByCache() throws SecurityException {
		
		List<Mtype> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(CacheIDs.MTYPES)) {
			list = (List<Mtype>) MemcachedUtil.get(CacheIDs.MTYPES);
		} else {
			// 从数据库中获取
			list = this.findByExample(new MtypeExample());
			MemcachedUtil.set(CacheIDs.MTYPES, 1000 * 60 * 30, list);
		}
		
		return list;
	}
	
}
