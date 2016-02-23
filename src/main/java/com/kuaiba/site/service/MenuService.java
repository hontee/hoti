package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.support.Pager;

public interface MenuService extends Pager<Menu, MenuExample> {
	
	int countByExample(MenuExample example);

    void deleteByExample(MenuExample example);

    void deleteByPrimaryKey(Long id);

    void add(Menu record);

    List<Menu> findByExample(MenuExample example);

    Menu findByPrimaryKey(Long id);

    void updateByExample(Menu record, MenuExample example);

    void updateByPrimaryKey(Menu record);

}
