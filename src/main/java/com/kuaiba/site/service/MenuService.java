package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.front.vo.MenuVO;
import com.kuaiba.site.service.kit.Pager;

public interface MenuService extends Pager<Menu, MenuExample> {
	
	int countByExample(MenuExample example);

    void deleteByExample(MenuExample example);

    void deleteByPrimaryKey(Long id);

    void add(MenuVO vo);

    List<Menu> findByExample(MenuExample example);

    Menu findByPrimaryKey(Long id);

    void updateByExample(Menu record, MenuExample example);

    void updateByPrimaryKey(Long id, MenuVO vo);

}
