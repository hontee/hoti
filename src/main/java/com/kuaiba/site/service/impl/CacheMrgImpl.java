package com.kuaiba.site.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.core.security.MemcachedUtil;
import com.kuaiba.site.db.dao.BookmarkFollowMapper;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.service.CacheMgr;
import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.service.DomainService;
import com.kuaiba.site.service.MenuService;
import com.kuaiba.site.service.MtypeService;

@Service
public class CacheMrgImpl implements CacheMgr {
	
	/*默认缓存时间*/
	private static final int DTIME = 1000 * 60 * 30;
	
	/*缓存常量*/
	private static final String MTYPES = "mtypes";
	private static final String CATES = "cates";
	private static final String DOMAINS = "domains";
	private static final String MENUS = "menus";
	private static final String USER_FOLLOW_BMS = "user_follow_bms";
	
	@Resource
	private MtypeService mtypeService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private DomainService domainService;
	@Resource
	private MenuService menuService;
	@Resource
	private BookmarkFollowMapper bfMapper;

	@SuppressWarnings("unchecked")
	public List<Mtype> readMtypes() throws SecurityException {
		
		List<Mtype> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(MTYPES)) {
			list = (List<Mtype>) MemcachedUtil.get(MTYPES);
		} else {
			list = mtypeService.findAll();
			MemcachedUtil.set(MTYPES, DTIME, list);
		}
		
		return list;
	}

	@Override
	public Mtype readMtype(Long id) throws SecurityException {
		
		for (Mtype mt : readMtypes()) {
			if (id.equals(mt.getId())) {
				return mt;
			}
		}
		
		return new Mtype();
	}

	@Override
	public void clearMtypes() throws SecurityException {
		if (MemcachedUtil.exists(MTYPES)) {
			MemcachedUtil.delete(MTYPES);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Category> readCates() throws SecurityException {

		List<Category> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(CATES)) {
			list = (List<Category>) MemcachedUtil.get(CATES);
		} else {
			list = categoryService.findAll();
			MemcachedUtil.set(CATES, DTIME, list);
		}
		
		return list;
	}

	@Override
	public void clearCates() throws SecurityException {
		if (MemcachedUtil.exists(CATES)) {
			MemcachedUtil.delete(CATES);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Domain> readDomains() throws SecurityException {

		List<Domain> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(DOMAINS)) {
			list = (List<Domain>) MemcachedUtil.get(DOMAINS);
		} else {
			list = domainService.findAll();
			MemcachedUtil.set(DOMAINS, DTIME, list);
		}
		
		return list;
	}

	@Override
	public void clearDomains() throws SecurityException {
		if (MemcachedUtil.exists(DOMAINS)) {
			MemcachedUtil.delete(DOMAINS);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Menu> readMenus() throws SecurityException {

		List<Menu> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(MENUS)) {
			list = (List<Menu>) MemcachedUtil.get(MENUS);
		} else {
			list = menuService.findAll();
			MemcachedUtil.set(MENUS, DTIME, list);
		}
		
		return list;
	}

	@Override
	public void clearMenus() throws SecurityException {
		if (MemcachedUtil.exists(MENUS)) {
			MemcachedUtil.delete(MENUS);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Long> readUserFollowBMS() throws SecurityException {

		List<Long> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(USER_FOLLOW_BMS)) {
			list = (List<Long>) MemcachedUtil.get(USER_FOLLOW_BMS);
		} else {
			list = bfMapper.selectByUid(AuthzUtil.getUserId());
			MemcachedUtil.set(USER_FOLLOW_BMS, DTIME, list);
		}
		
		return list;
	}

	@Override
	public void clearUserFollowBMS() throws SecurityException {
		if (MemcachedUtil.exists(USER_FOLLOW_BMS)) {
			MemcachedUtil.delete(USER_FOLLOW_BMS);
		}
	}

}
