package com.hoti.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.CreateException;
import com.hoti.site.core.exception.DeleteException;
import com.hoti.site.core.exception.ReadException;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.core.exception.UpdateException;
import com.hoti.site.core.exception.ValidationException;
import com.hoti.site.core.security.AuthzUtil;
import com.hoti.site.core.security.MemcachedUtil;
import com.hoti.site.db.dao.CategoryMapper;
import com.hoti.site.db.dao.MapMapper;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.CategoryExample;
import com.hoti.site.db.entity.PagerUtil;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.VUtil;
import com.hoti.site.front.vo.CategoryVO;
import com.hoti.site.service.CachePolicy;
import com.hoti.site.service.CategoryService;

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
  public void delete(CategoryExample example) throws SecurityException {
    try {
      MemcachedUtil.delete("cates");
      VUtil.assertNotNull(example);
      mapper.deleteByExample(example);
    } catch (Exception e) {
      throw new DeleteException("删除分类失败", e);
    }
  }

  @Override
  public void delete(Long id) throws SecurityException {
    try {
      MemcachedUtil.delete("cates");
      VUtil.assertNotNull(id);
      mapper.deleteByPrimaryKey(id);
    } catch (Exception e) {
      throw new DeleteException("删除分类失败", e);
    }
  }

  @Override
  public void add(CategoryVO vo) throws SecurityException {
    try {
      MemcachedUtil.delete("cates");
      VUtil.assertNotNull(vo);
      Category record = new Category();
      record.setName(vo.getName());
      record.setTitle(vo.getTitle());
      record.setDescription(vo.getDescription());
      record.setState(vo.getState());
      record.setParent(vo.getParent());
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
  public void update(Category record, CategoryExample example) throws SecurityException {
    try {
      MemcachedUtil.delete("cates");
      VUtil.assertNotNull(record, example);
      mapper.updateByExample(record, example);
    } catch (Exception e) {
      throw new UpdateException("更新分类失败", e);
    }
  }

  @Override
  public void update(Long id, CategoryVO vo) throws SecurityException {
    try {
      MemcachedUtil.delete("cates");
      VUtil.assertNotNull(vo, id);
      Category record = new Category();
      record.setId(id);
      record.setName(vo.getName());
      record.setTitle(vo.getTitle());
      record.setDescription(vo.getDescription());
      record.setState(vo.getState());
      record.setParent(vo.getParent());
      mapper.updateByPrimaryKey(record);
    } catch (Exception e) {
      throw new UpdateException("更新分类失败", e);
    }
  }

  @Override
  public void update(Long id, long count, long groupCount) throws SecurityException {
    try {
      MemcachedUtil.delete("cates");
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
