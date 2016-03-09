package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import com.kuaiba.site.front.vo.MtypeVO;
import com.kuaiba.site.service.utils.Pager;

public interface MtypeService extends Pager<Mtype, MtypeExample> {
	
    int countByExample(MtypeExample example);

    void deleteByExample(MtypeExample example);

    void deleteByPrimaryKey(Long id);

    void add(MtypeVO vo);

    List<Mtype> findByExample(MtypeExample example);

    Mtype findByPrimaryKey(Long id);

    void updateByExample(Mtype record, MtypeExample example);

    void updateByPrimaryKey(Long id, MtypeVO vo);
    
    /**
	 * 验证MType名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkMTypeName(String name);
	
	/**
	 * 验证MType标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkMTypeTitle(String title);

}
