package com.hoti.site.service;

import java.util.List;

import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.Menu;

/**
 * 缓存管理: 只负责read缓存
 * @author larry.qi
 */
public interface CachePolicy {
	
	/**
	 * 从缓存中读取分类列表
	 * @return
	 */
	List<Category> readCates() throws SecurityException;
	
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
