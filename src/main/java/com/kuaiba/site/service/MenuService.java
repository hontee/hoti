package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Attribute;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.front.vo.MenuVO;

public interface MenuService extends Pager<Menu, MenuExample> {
	
	/**
	 * 按条件统计
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
	int count(MenuExample example) throws SecurityException;

	/**
	 * 按条件删除
	 * @param example
	 * @throws SecurityException
	 */
    void delete(MenuExample example) throws SecurityException;

    /**
     * 删除
     * @param id
     * @throws SecurityException
     */
    void delete(Long id) throws SecurityException;

    /**
     * 添加菜单
     * @param vo
     * @throws SecurityException
     */
    void add(MenuVO vo) throws SecurityException;
    
    /**
     * 获取数据列表
     * @return
     * @throws SecurityException
     */
    List<Menu> findAll() throws SecurityException;

    /**
     * 获取数据列表
     * @param example
     * @return
     * @throws SecurityException
     */
    List<Menu> findAll(MenuExample example) throws SecurityException;

    /**
     * 获取数据
     * @param id
     * @return
     * @throws SecurityException
     */
    Menu findOne(Long id) throws SecurityException;

    /**
     * 按条件更新
     * @param record
     * @param example
     * @throws SecurityException
     */
    void update(Menu record, MenuExample example) throws SecurityException;

    /**
     * 更新
     * @param id
     * @param vo
     * @throws SecurityException
     */
    void update(Long id, MenuVO vo) throws SecurityException;
    
    /**
     * 验证属性值是否存在
     * @param attr
     * @param value
     * @return
     * @throws SecurityException
     */
	boolean validate(Attribute attr, String value) throws SecurityException;
	
}
