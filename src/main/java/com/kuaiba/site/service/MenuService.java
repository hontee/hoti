package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.cache.MenuCachePolicy;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.front.vo.MenuVO;

public interface MenuService extends Pager<Menu, MenuExample>, MenuCachePolicy {
	
	int countByExample(MenuExample example) throws SecurityException;

    void deleteByExample(MenuExample example) throws SecurityException;

    void deleteByPrimaryKey(Long id) throws SecurityException;

    void add(MenuVO vo) throws SecurityException;

    List<Menu> findByExample(MenuExample example) throws SecurityException;

    Menu findByPrimaryKey(Long id) throws SecurityException;

    void updateByExample(Menu record, MenuExample example) throws SecurityException;

    void updateByPrimaryKey(Long id, MenuVO vo) throws SecurityException;
    
    /**
	 * 验证Menu名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkMenuName(String name) throws SecurityException;
	
	/**
	 * 验证Menu标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkMenuTitle(String title) throws SecurityException;

}
