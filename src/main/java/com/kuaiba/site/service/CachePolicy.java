package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.interceptor.ClearCache;

/**
 * 缓存管理: 只负责read缓存，clear缓存 #{@link ClearCache}
 * @author larry.qi
 */
public interface CachePolicy {
	
	/**
	 * 从缓存中读取类型列表
	 * @return
	 */
	List<Mtype> readMtypes() throws SecurityException;
	
	/**
	 * 从缓存中读取类型
	 * @return
	 */
	Mtype readMtype(Long id) throws SecurityException;
	
	/**
	 * 从缓存中读取分类列表
	 * @return
	 */
	List<Category> readCates() throws SecurityException;
	
	/**
	 * 从缓存中读取领域
	 * @return
	 */
	List<Domain> readDomains() throws SecurityException;
	
	/**
	 * 从缓存中读取菜单
	 * @return
	 */
	List<Menu> readMenus() throws SecurityException;
	
	/**
	 * 从缓存中读取登录用户关注的站点IDs
	 * @return
	 */
	List<Long> readUserFollowBMS() throws SecurityException;
	
	/**
	 * 从缓存中读取登录用户关注的群组IDs
	 * @return
	 */
	List<Long> readUserFollowGroup() throws SecurityException;
	
}
