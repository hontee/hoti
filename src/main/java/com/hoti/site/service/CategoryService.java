package com.hoti.site.service;

import java.util.List;

import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.CategoryExample;
import com.hoti.site.db.entity.Pager;
import com.hoti.site.front.vo.CategoryVO;

public interface CategoryService extends Pager<Category, CategoryExample> {
	
	/**
	 * 按条件统计
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
    int count(CategoryExample example) throws SecurityException;

    /**
     * 按条件删除
     * @param example
     * @throws SecurityException
     */
    void delete(CategoryExample example) throws SecurityException;

    /**
     * 按ID删除
     * @param id
     * @throws SecurityException
     */
    void delete(Long id) throws SecurityException;

    /**
     * 添加
     * @param vo
     * @throws SecurityException
     */
	void add(CategoryVO vo) throws SecurityException;
	
	/**
	 * 读取数据
	 * @return
	 * @throws SecurityException
	 */
    List<Category> findAll() throws SecurityException;

	/**
	 * 读取数据
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
    List<Category> findAll(CategoryExample example) throws SecurityException;

    /**
     * 读取一个数据
     * @param id
     * @return
     * @throws SecurityException
     */
    Category findOne(Long id) throws SecurityException;
    
    /**
     * 按条件更新
     * @param record
     * @param example
     * @throws SecurityException
     */
    void update(Category record, CategoryExample example) throws SecurityException;

    /**
     * 更新
     * @param id
     * @param vo
     * @throws SecurityException
     */
    void update(Long id, CategoryVO vo) throws SecurityException;
    
    /**
     * 更新统计
     * @param id
     * @param count
     * @throws SecurityException
     */
    void update(Long id, long count, long groupCount) throws SecurityException;

    /**
	 * 验证Category名称是否存在
	 * @param name
	 * @return
	 */
	boolean validate(String name) throws SecurityException;
	
}
