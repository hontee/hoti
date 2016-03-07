package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import com.kuaiba.site.front.vo.MtypeVO;
import com.kuaiba.site.service.kit.Pager;

public interface MtypeService extends Pager<Mtype, MtypeExample> {
	
    int countByExample(MtypeExample example);

    void deleteByExample(MtypeExample example);

    void deleteByPrimaryKey(Long id);

    void add(MtypeVO vo);

    List<Mtype> findByExample(MtypeExample example);

    Mtype findByPrimaryKey(Long id);

    void updateByExample(Mtype record, MtypeExample example);

    void updateByPrimaryKey(Long id, MtypeVO vo);

}
