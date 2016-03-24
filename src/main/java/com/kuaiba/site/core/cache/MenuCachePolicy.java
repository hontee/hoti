package com.kuaiba.site.core.cache;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Menu;

/**
 * 菜单缓存接口
 * @author larry.qi
 */
public interface MenuCachePolicy {
	
	/**
	 * 获取所有菜单
	 * @return
	 * @throws SecurityException
	 */
	public List<Menu> getMenus() throws SecurityException;

}
