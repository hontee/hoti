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
import com.hoti.site.db.dao.MenuMapper;
import com.hoti.site.db.entity.Attribute;
import com.hoti.site.db.entity.Menu;
import com.hoti.site.db.entity.MenuExample;
import com.hoti.site.db.entity.PagerUtil;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.VUtil;
import com.hoti.site.front.vo.MenuVO;
import com.hoti.site.service.CachePolicy;
import com.hoti.site.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

  @Resource
  private MenuMapper mapper;
  @Resource
  private CachePolicy cacheMgr;

  @Override
  public PageInfo<Menu> find(MenuExample example, Pagination p) throws SecurityException {
    try {
      VUtil.assertNotNull(example, p);
      PagerUtil.startPage(p);
      List<Menu> list = findAll(example);
      return new PageInfo<>(list);
    } catch (Exception e) {
      throw new ReadException("分页读取菜单失败", e);
    }
  }

  @Override
  public int count(MenuExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(example);
      return mapper.countByExample(example);
    } catch (Exception e) {
      throw new ReadException("统计菜单失败", e);
    }
  }

  @Override
  public void delete(MenuExample example) throws SecurityException {
    try {
      MemcachedUtil.delete("menus");
      VUtil.assertNotNull(example);
      mapper.deleteByExample(example);
    } catch (Exception e) {
      throw new DeleteException("删除菜单失败", e);
    }
  }

  @Override
  public void delete(Long id) throws SecurityException {
    try {
      MemcachedUtil.delete("menus");
      VUtil.assertNotNull(id);
      mapper.deleteByPrimaryKey(id);
    } catch (Exception e) {
      throw new DeleteException("删除菜单失败", e);
    }
  }

  @Override
  public void add(MenuVO vo) throws SecurityException {
    try {
      MemcachedUtil.delete("menus");
      VUtil.assertNotNull(vo);
      Menu record = new Menu();
      record.setCreator(AuthzUtil.getUsername());
      record.setDescription(vo.getDescription());
      record.setName(vo.getName());
      record.setState(vo.getState());
      record.setTitle(vo.getTitle());
      record.setPath(vo.getPath());
      record.setWeight(vo.getWeight());
      record.setOrganization(vo.getOrganization());
      mapper.insert(record);
    } catch (Exception e) {
      throw new CreateException("添加菜单失败", e);
    }
  }

  @Override
  public List<Menu> findAll() throws SecurityException {
    return cacheMgr.readMenus();
  }

  @Override
  public List<Menu> findAll(MenuExample example) throws SecurityException {
    try {
      VUtil.assertNotNull(example);
      return mapper.selectByExample(example);
    } catch (Exception e) {
      throw new ReadException("读取菜单失败", e);
    }
  }

  @Override
  public Menu findOne(Long id) throws SecurityException {
    try {
      VUtil.assertNotNull(id);
      return mapper.selectByPrimaryKey(id);
    } catch (Exception e) {
      throw new ReadException("读取菜单失败", e);
    }
  }

  @Override
  public void update(Menu record, MenuExample example) throws SecurityException {
    try {
      MemcachedUtil.delete("menus");
      VUtil.assertNotNull(record, example);
      mapper.updateByExample(record, example);
    } catch (Exception e) {
      throw new UpdateException("更新菜单失败", e);
    }
  }

  @Override
  public void update(Long id, MenuVO vo) throws SecurityException {
    try {
      MemcachedUtil.delete("menus");
      VUtil.assertNotNull(vo, id);
      Menu record = new Menu();
      record.setId(id);
      record.setDescription(vo.getDescription());
      record.setName(vo.getName());
      record.setState(vo.getState());
      record.setTitle(vo.getTitle());
      record.setPath(vo.getPath());
      record.setWeight(vo.getWeight());
      record.setOrganization(vo.getOrganization());
      mapper.updateByPrimaryKey(record);
    } catch (Exception e) {
      throw new UpdateException("更新菜单失败", e);
    }
  }

  @Override
  public boolean validate(Attribute attr, String value) throws SecurityException {
    try {
      VUtil.assertNotNull(value);
      MenuExample example = new MenuExample();

      if (attr == Attribute.TITLE) {
        example.createCriteria().andTitleEqualTo(value);
      } else { // Attribute.NAME
        example.createCriteria().andNameEqualTo(value);
      }

      return !mapper.selectByExample(example).isEmpty();
    } catch (Exception e) {
      throw new ValidationException("验证菜单" + attr.name() + "失败", e);
    }
  }

}
