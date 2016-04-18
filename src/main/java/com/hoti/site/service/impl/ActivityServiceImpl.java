package com.hoti.site.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.DeleteException;
import com.hoti.site.core.exception.ReadException;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.core.security.AuthzUtil;
import com.hoti.site.core.security.ThreadUtil;
import com.hoti.site.db.dao.ActivityMapper;
import com.hoti.site.db.entity.Activity;
import com.hoti.site.db.entity.ActivityExample;
import com.hoti.site.db.entity.HttpUtil;
import com.hoti.site.db.entity.PagerUtil;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.VUtil;
import com.hoti.site.service.ActivityService;

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
      mapper.batchDelete(ids);
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
  public void addLogger(String name, String tbl, String desc, HttpServletRequest request)
      throws SecurityException {
    ThreadUtil.execute(new Runnable() {
      public void run() {
        try {
          // 内容格式：管理员[admin], 后台指删除异常: + desc
          String ut = AuthzUtil.isAdmin() ? "管理员" : "用户";
          String description = String.format("%s[%s], %s: ", ut, AuthzUtil.getUsername(), name, desc);

          Activity record = new Activity();
          record.setCreator(AuthzUtil.getUsername());
          record.setUserType(AuthzUtil.isAdmin() ? "admin" : "user");
          record.setIp(HttpUtil.getIpAddr(request));
          record.setState((byte) 1);
          record.setDescription(description);
          record.setName(name);
          record.setTbl(tbl);
          mapper.insert(record);
        } catch (Exception e) {}
      }
    });
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
