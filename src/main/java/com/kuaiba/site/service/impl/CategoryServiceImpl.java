package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.core.exception.ValidationException;
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.db.dao.MapMapper;
import com.kuaiba.site.db.dao.CategoryMapper;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.PagerUtil;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.VUtil;
import com.kuaiba.site.front.vo.CategoryVO;
import com.kuaiba.site.interceptor.ClearCache;
import com.kuaiba.site.service.CachePolicy;
import com.kuaiba.site.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Resource
	private MapMapper bfMapper;
	@Resource
	private CategoryMapper mapper;
	@Resource
	private CachePolicy cacheMgr;

	@Override
	public PageInfo<Category> find(CategoryExample example, Pagination p) throws SecurityException {
		try {
			VUtil.assertNotNull(example, p);
			PagerUtil.startPage(p);
			List<Category> list = findAll(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取分类失败", e);
		}
	}

	@Override
	public int count(CategoryExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计分类失败", e);
		}
	}

	@Override
	@ClearCache("cates")
	public void delete(CategoryExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除分类失败", e);
		}
	}

	@Override
	@ClearCache("cates")
	public void delete(Long id) throws SecurityException {
		try {
			VUtil.assertNotNull(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除分类失败", e);
		}
	}

	@Override
	@ClearCache("cates")
	public void add(CategoryVO vo) throws SecurityException {
		try {
			VUtil.assertNotNull(vo);
			Category record = new Category();
			record.setName(vo.getName());
			record.setTitle(vo.getTitle());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setDomain(vo.getDomain());
			record.setCreateBy(AuthzUtil.getUserId());
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加分类失败", e);
		}
	}

	@Override
	public List<Category> findAll() throws SecurityException {
		return cacheMgr.readCates();
	}

	@Override
	public List<Category> findAll(CategoryExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取分类失败", e);
		}
	}

	@Override
	public Category findOne(Long id) throws SecurityException {
		try {
			VUtil.assertNotNull(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取分类失败", e);
		}
	}

	@Override
	@ClearCache("cates")
	public void update(Category record, CategoryExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新分类失败", e);
		}
	}

	@Override
	@ClearCache("cates")
	public void update(Long id, CategoryVO vo) throws SecurityException { 
		try {
			VUtil.assertNotNull(vo, id);
			Category record = new Category();
			record.setId(id);
			record.setName(vo.getName());
			record.setTitle(vo.getTitle());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setDomain(vo.getDomain());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新分类失败", e);
		}
	}
	
	@Override
	@ClearCache("cates")
	public void update(Long id, long count, long groupCount) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			Category record = new Category();
			record.setId(id);
			record.setCount(count);
			record.setGroupCount(groupCount);
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新统计分类失败", e);
		}
	}

	@Override
	public List<Category> find(Long domain) throws SecurityException { 
		try {
			VUtil.assertNotNull(domain);
			return mapper.selectByDomain(domain);
		} catch (Exception e) {
			throw new ReadException("读取分类失败", e);
		}
	}
	
	@Override
	public boolean validate(String name) throws SecurityException { 
		try {
			VUtil.assertNotNull(name);
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(name);
			return !mapper.selectByExample(example).isEmpty();
		} catch (Exception e) {
			throw new ValidationException("检测分类名称失败", e);
		}
	}
	
}
