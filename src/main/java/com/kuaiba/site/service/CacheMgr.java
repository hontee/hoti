package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.Mtype;

/**
 * 缓存管理
 * @author larry.qi
 */
public interface CacheMgr {
	
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
	 * 清除类型缓存
	 */
	void clearMtypes() throws SecurityException;
	
	/**
	 * 从缓存中读取分类列表
	 * @return
	 */
	List<Category> readCates() throws SecurityException;
	
	/**
	 * 清除分类缓存
	 */
	void clearCates() throws SecurityException;
	
	/**
	 * 从缓存中读取领域
	 * @return
	 */
	List<Domain> readDomains() throws SecurityException;
	
	/**
	 * 清除领域缓存
	 */
	void clearDomains() throws SecurityException;
	
	/**
	 * 从缓存中读取菜单
	 * @return
	 */
	List<Menu> readMenus() throws SecurityException;
	
	/**
	 * 清除缓存中的菜单
	 */
	void clearMenus() throws SecurityException;
	
	/**
	 * 从缓存中读取登录用户关注的站点IDs
	 * @return
	 */
	List<Long> readUserFollowBMS() throws SecurityException;
	
	/**
	 * 清除缓存中的登录用户关注的站点IDs
	 */
	void clearUserFollowBMS() throws SecurityException;
	
}
