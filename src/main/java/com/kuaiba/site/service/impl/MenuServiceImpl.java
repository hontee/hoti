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
import com.kuaiba.site.db.dao.MenuMapper;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.front.vo.MenuVO;
import com.kuaiba.site.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Resource
	private MenuMapper mapper;

	@Override
	public PageInfo<Menu> findByExample(MenuExample example, Pagination p) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Menu> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取菜单失败", e);
		}
	}

	@Override
	public int countByExample(MenuExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计菜单失败", e);
		}
	}

	@Override
	public void deleteByExample(MenuExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除菜单失败", e);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除菜单失败", e);
		}
	}

	@Override
	public void add(MenuVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			Menu record = new Menu();
			record.setCreator(CurrentUser.getCurrentUserName());
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
	public List<Menu> findByExample(MenuExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取菜单失败", e);
		}
	}

	@Override
	public Menu findByPrimaryKey(Long id) throws SecurityException { 
		
		ContraintValidator.checkPrimaryKey(id);
		List<Menu> list = this.getMenus();
		
		for (Menu m : list) {
			return m;
		}
		
		return new Menu();
	}

	@Override
	public void updateByExample(Menu record, MenuExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新菜单失败", e);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, MenuVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			ContraintValidator.checkPrimaryKey(id);
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
	public boolean checkMenuName(String name) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(name);
			MenuExample example = new MenuExample();
			example.createCriteria().andNameEqualTo(name);
			List<Menu> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
			throw new ReadException("检测菜单名称失败", e);
		}
	}

	@Override
	public boolean checkMenuTitle(String title) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(title);
			MenuExample example = new MenuExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Menu> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
			throw new ReadException("检测菜单标题失败", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getMenus() throws SecurityException {

		List<Menu> list = new ArrayList<>();
		
		if (Memcacheds.exists(CacheIDs.MENUS)) {
			list = (List<Menu>) Memcacheds.get(CacheIDs.MENUS);
		} else {
			// 从数据库中获取
			list = this.findByExample(new MenuExample());
			Memcacheds.set(CacheIDs.MENUS, 1000 * 60 * 30, list);
		}
		
		return list;
	}

}
