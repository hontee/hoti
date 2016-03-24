package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.cache.MtypeCachePolicy;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.front.vo.MtypeVO;

public interface MtypeService extends Pager<Mtype, MtypeExample>, MtypeCachePolicy {
	
	/**
	 * 按条件统计
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
    int count(MtypeExample example) throws SecurityException;

    /**
     * 按条件删除
     * @param example
     * @throws SecurityException
     */
    void delete(MtypeExample example) throws SecurityException;

    /**
     * 删除
     * @param id
     * @throws SecurityException
     */
    void delete(Long id) throws SecurityException;

    /**
     * 添加
     * @param vo
     * @throws SecurityException
     */
    void add(MtypeVO vo) throws SecurityException;

    /**
     * 获取数据列表
     * @param example
     * @return
     * @throws SecurityException
     */
    List<Mtype> read(MtypeExample example) throws SecurityException;

    /**
     * 获取数据
     * @param id
     * @return
     * @throws SecurityException
     */
    Mtype read(Long id) throws SecurityException;
    
    /**
     * 按条件更新
     * @param record
     * @param example
     * @throws SecurityException
     */
    void update(Mtype record, MtypeExample example) throws SecurityException;

    /**
     * 更新
     * @param id
     * @param vo
     * @throws SecurityException
     */
    void update(Long id, MtypeVO vo) throws SecurityException;
    
    /**
     * 验证属性值是否存在
     * @param attr
     * @param value
     * @return
     * @throws SecurityException
     */
	boolean validate(Mtype.Attrs attr, String value) throws SecurityException;
	
}
