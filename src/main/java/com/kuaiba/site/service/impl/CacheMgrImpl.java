package com.kuaiba.site.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.core.security.MemcachedUtil;
import com.kuaiba.site.db.dao.BookmarkFollowMapper;
import com.kuaiba.site.db.dao.CategoryMapper;
import com.kuaiba.site.db.dao.DomainMapper;
import com.kuaiba.site.db.dao.MenuMapper;
import com.kuaiba.site.db.dao.MtypeMapper;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import com.kuaiba.site.service.CachePolicy;

@Service
public class CacheMgrImpl implements CachePolicy {
	
	/*默认缓存时间*/
	private final int DTIME = 1000 * 60 * 30;
	
	/*缓存常量*/
	private final String MTYPES = "mtypes";
	private final String CATES = "cates";
	private final String DOMAINS = "domains";
	private final String MENUS = "menus";
	private final String USER_FOLLOW_BMS = "user_follow_bms";
	
	@Resource
	private MtypeMapper mm;
	@Resource
	private CategoryMapper cm;
	@Resource
	private DomainMapper dm;
	@Resource
	private MenuMapper menum;
	@Resource
	private BookmarkFollowMapper bfm;

	@SuppressWarnings("unchecked")
	public List<Mtype> readMtypes() throws SecurityException {
		
		List<Mtype> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(MTYPES)) {
			list = (List<Mtype>) MemcachedUtil.get(MTYPES);
		} else {
			MtypeExample example = new MtypeExample();
			example.createCriteria().andStateEqualTo((byte)1);
			example.setOrderByClause("weight DESC"); // 按权重排序
			list = mm.selectByExample(example);
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

	@SuppressWarnings("unchecked")
	public List<Category> readCates() throws SecurityException {

		List<Category> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(CATES)) {
			list = (List<Category>) MemcachedUtil.get(CATES);
		} else {
			CategoryExample example = new CategoryExample();
			example.createCriteria().andStateEqualTo((byte)1);
			list = cm.selectByExample(example);
			MemcachedUtil.set(CATES, DTIME, list);
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Domain> readDomains() throws SecurityException {

		List<Domain> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(DOMAINS)) {
			list = (List<Domain>) MemcachedUtil.get(DOMAINS);
		} else {
			DomainExample example = new DomainExample();
			example.createCriteria().andStateEqualTo((byte)1);
			example.setOrderByClause("weight DESC"); // 按权重排序
			list = dm.selectByExample(example);
			MemcachedUtil.set(DOMAINS, DTIME, list);
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Menu> readMenus() throws SecurityException {

		List<Menu> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(MENUS)) {
			list = (List<Menu>) MemcachedUtil.get(MENUS);
		} else {
			MenuExample example = new MenuExample();
			example.createCriteria().andStateEqualTo((byte)1);
			example.setOrderByClause("weight DESC"); // 按权重排序
			list = menum.selectByExample(new MenuExample());
			MemcachedUtil.set(MENUS, DTIME, list);
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Long> readUserFollowBMS() throws SecurityException {

		List<Long> list = new ArrayList<>();
		
		if (MemcachedUtil.exists(USER_FOLLOW_BMS)) {
			list = (List<Long>) MemcachedUtil.get(USER_FOLLOW_BMS);
		} else {
			list = bfm.selectByUid(AuthzUtil.getUserId());
			MemcachedUtil.set(USER_FOLLOW_BMS, DTIME, list);
		}
		
		return list;
	}

}
