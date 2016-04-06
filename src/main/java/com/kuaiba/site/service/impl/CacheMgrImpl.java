package com.kuaiba.site.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.core.security.MemcachedUtil;
import com.kuaiba.site.db.dao.BookmarkFollowMapper;
import com.kuaiba.site.db.dao.CategoryMapper;
import com.kuaiba.site.db.dao.DomainMapper;
import com.kuaiba.site.db.dao.GroupFollowMapper;
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
	
	private Logger logger = LoggerFactory.getLogger(CacheMgrImpl.class);
	
	/*默认缓存时间*/
	private final int DTIME = 1000 * 60 * 30;
	
	/*缓存常量*/
	private final String MTYPES = "mtypes";
	private final String CATES = "cates";
	private final String DOMAINS = "domains";
	private final String MENUS = "menus";
	private final String USER_FOLLOW_BMS = "user_follow_bms";
	private final String USER_FOLLOW_GROUP = "user_follow_group";
	
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
	@Resource
	private GroupFollowMapper gfm;

	
	public List<Mtype> readMtypes() throws SecurityException {
		
		/*Object obj = MemcachedUtil.get(MTYPES);*/
		List<Mtype> list = new ArrayList<>();
		MtypeExample example = new MtypeExample();
		example.createCriteria().andStateEqualTo((byte)1);
		example.setOrderByClause("weight DESC"); // 按权重排序
		list = mm.selectByExample(example);
		MemcachedUtil.set(MTYPES, DTIME, list);
		
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

	
	public List<Category> readCates() throws SecurityException {

		/*Object obj = MemcachedUtil.get(CATES);*/
		List<Category> list = new ArrayList<>();

		CategoryExample example = new CategoryExample();
		example.createCriteria().andStateEqualTo((byte)1);
		list = cm.selectByExample(example);
		MemcachedUtil.set(CATES, DTIME, list);
		
		return list;
	}

	
	public List<Domain> readDomains() throws SecurityException {

		/*Object obj = MemcachedUtil.get(DOMAINS);*/
		List<Domain> list = new ArrayList<>();
		
		DomainExample example = new DomainExample();
		example.createCriteria().andStateEqualTo((byte)1);
		example.setOrderByClause("weight DESC"); // 按权重排序
		list = dm.selectByExample(example);
		MemcachedUtil.set(DOMAINS, DTIME, list);
		
		return list;
	}

	
	public List<Menu> readMenus() throws SecurityException {

		/*Object obj = MemcachedUtil.get(MENUS);*/
		List<Menu> list = new ArrayList<>();
		
		MenuExample example = new MenuExample();
		example.createCriteria().andStateEqualTo((byte)1);
		example.setOrderByClause("weight DESC"); // 按权重排序
		list = menum.selectByExample(new MenuExample());
		MemcachedUtil.set(MENUS, DTIME, list);
		
		return list;
	}

	
	public List<Long> readUserFollowBMS() throws SecurityException {

		/*Object obj = MemcachedUtil.get(USER_FOLLOW_BMS);*/
		List<Long> list = new ArrayList<>();
		
		list = bfm.selectByUserId(AuthzUtil.getUserId());
		MemcachedUtil.set(USER_FOLLOW_BMS, DTIME, list);
		
		logger.info("用户关注的书签：" + JSON.toJSONString(list));
		
		return list;
	}

	
	
	public List<Long> readUserFollowGroup() throws SecurityException {
		
		/*Object obj = MemcachedUtil.get(USER_FOLLOW_GROUP);*/
		List<Long> list = new ArrayList<>();
		
		list = gfm.selectByUserId(AuthzUtil.getUserId());
		MemcachedUtil.set(USER_FOLLOW_GROUP, DTIME, list);
		
		logger.info("用户关注的群组：" + JSON.toJSONString(list));
		
		return list;
	}

}
