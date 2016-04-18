package com.hoti.site.service;

import java.util.List;

import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Pager;
import com.hoti.site.db.entity.Region;
import com.hoti.site.db.entity.RegionExample;

public interface RegionService extends Pager<Region, RegionExample> {

  /**
   * 统计
   * @param example
   * @return
   * @throws SecurityException
   */
  int count(RegionExample example) throws SecurityException;

  /**
   * 删除
   * @param example
   * @throws SecurityException
   */
  void delete(RegionExample example) throws SecurityException;

  /**
   * 删除
   * @param id
   * @throws SecurityException
   */
  void delete(Integer id) throws SecurityException;

  /**
   * 添加
   * @param record
   * @throws SecurityException
   */
  void add(Region record) throws SecurityException;

  /**
   * 查询
   * @param example
   * @return
   * @throws SecurityException
   */
  List<Region> findAll(RegionExample example) throws SecurityException;

  /**
   * 查询
   * @param id
   * @return
   * @throws SecurityException
   */
  Region findOne(Integer id) throws SecurityException;

  /**
   * 更新
   * @param record
   * @param example
   * @throws SecurityException
   */
  void update(Region record, RegionExample example) throws SecurityException;

  /**
   * 更新
   * @param record
   * @throws SecurityException
   */
  void update(Region record) throws SecurityException;
  
}
