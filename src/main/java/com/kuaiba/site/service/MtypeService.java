package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.cache.MtypeCache;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.front.vo.MtypeVO;

public interface MtypeService extends Pager<Mtype, MtypeExample>, MtypeCache {
	
    int countByExample(MtypeExample example) throws SecurityException;

    void deleteByExample(MtypeExample example) throws SecurityException;

    void deleteByPrimaryKey(Long id) throws SecurityException;

    void add(MtypeVO vo) throws SecurityException;

    List<Mtype> findByExample(MtypeExample example) throws SecurityException;

    Mtype findByPrimaryKey(Long id) throws SecurityException;

    void updateByExample(Mtype record, MtypeExample example) throws SecurityException;

    void updateByPrimaryKey(Long id, MtypeVO vo) throws SecurityException;
    
    /**
	 * 验证MType名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkMTypeName(String name) throws SecurityException;
	
	/**
	 * 验证MType标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkMTypeTitle(String title) throws SecurityException;

}
