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
import com.hoti.site.db.dao.RegionMapper;
import com.hoti.site.db.entity.PagerUtil;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.Region;
import com.hoti.site.db.entity.RegionExample;
import com.hoti.site.db.entity.VUtil;
import com.hoti.site.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {
  
  @Resource
  private RegionMapper rm;

  @Override
  public PageInfo<Region> find(RegionExample example, Pagination p) throws SecurityException {
    try {
      VUtil.assertNotNull(example, p);
      PagerUtil.startPage(p);
      List<Region> list = findAll(example);
      return new PageInfo<>(list);
    } catch (Exception e) {
      throw new ReadException("分页读取城市失败", e);
    }
  }

  @Override
  public int count(RegionExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(example);
      return rm.countByExample(example);
    } catch (Exception e) {
      throw new ReadException("统计城市失败", e);
    }
  }

  @Override
  public void delete(RegionExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(example);
      rm.deleteByExample(example);
    } catch (Exception e) {
      throw new DeleteException("删除城市失败", e);
    }
  }

  @Override
  public void delete(Integer id) throws SecurityException {
    try {
      VUtil.assertNotNull(id);
      rm.deleteByPrimaryKey(id);
    } catch (Exception e) {
      throw new DeleteException("删除城市失败", e);
    }
  }

  @Override
  public void add(Region record) throws SecurityException {
    try {
      VUtil.assertNotNull(record);
      rm.insert(record);
    } catch (Exception e) {
      throw new CreateException("添加城市失败", e);
    }
  }

  @Override
  public List<Region> findAll(RegionExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(example);
      return rm.selectByExample(example);
    } catch (Exception e) {
      throw new ReadException("读取城市失败", e);
    }
  }

  @Override
  public Region findOne(Integer id) throws SecurityException {
    try {
      VUtil.assertNotNull(id);
      return rm.selectByPrimaryKey(id);
    } catch (Exception e) {
      throw new ReadException("读取城市失败", e);
    }
  }

  @Override
  public void update(Region record, RegionExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(record, example);
      rm.updateByExample(record, example);
    } catch (Exception e) {
      throw new UpdateException("更新城市失败", e);
    }
  }

  @Override
  public void update(Region record) throws SecurityException {
    try {
      VUtil.assertNotNull(record);
      rm.updateByPrimaryKey(record);
    } catch (Exception e) {
      throw new UpdateException("更新城市失败", e);
    }
  }

 

}
