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
import com.kuaiba.site.db.entity.Menu.Attrs;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.front.vo.MenuVO;
import com.kuaiba.site.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Resource
	private MenuMapper mapper;

	@Override
	public PageInfo<Menu> search(MenuExample example, Pagination p) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Menu> list = read(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取菜单失败", e);
		}
	}

	@Override
	public int count(MenuExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计菜单失败", e);
		}
	}

	@Override
	public void delete(MenuExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除菜单失败", e);
		}
	}

	@Override
	public void delete(Long id) throws SecurityException { 
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
	public List<Menu> read(MenuExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取菜单失败", e);
		}
	}

	@Override
	public Menu read(Long id) throws SecurityException { 
		
		ContraintValidator.checkPrimaryKey(id);
		List<Menu> list = this.getMenus();
		
		for (Menu m : list) {
			return m;
		}
		
		return new Menu();
	}

	@Override
	public void update(Menu record, MenuExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新菜单失败", e);
		}
	}

	@Override
	public void update(Long id, MenuVO vo) throws SecurityException { 
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
	public boolean validate(Attrs attr, String value) throws SecurityException {
		ContraintValidator.checkNotNull(value);
		MenuExample example = new MenuExample();
		
		if (attr == Menu.Attrs.NAME) {
			example.createCriteria().andNameEqualTo(value);
		} else if (attr == Menu.Attrs.TITLE) {
			example.createCriteria().andTitleEqualTo(value);
		}
		
		return !mapper.selectByExample(example).isEmpty();
	}


	@SuppressWarnings("unchecked")
	public List<Menu> getMenus() throws SecurityException {

		List<Menu> list = new ArrayList<>();
		
		if (Memcacheds.exists(CacheIDs.MENUS)) {
			list = (List<Menu>) Memcacheds.get(CacheIDs.MENUS);
		} else {
			// 从数据库中获取
			list = read(new MenuExample());
			Memcacheds.set(CacheIDs.MENUS, 1000 * 60 * 30, list);
		}
		
		return list;
	}

}
