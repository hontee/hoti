package com.hoti.site.service;

import java.util.List;

import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Attribute;
import com.hoti.site.db.entity.Domain;
import com.hoti.site.db.entity.DomainExample;
import com.hoti.site.db.entity.Pager;
import com.hoti.site.front.vo.DomainVO;

public interface DomainService extends Pager<Domain, DomainExample> {

	/**
	 * 按条件统计
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
	int count(DomainExample example) throws SecurityException;

	/**
	 * 按条件删除
	 * @param example
	 * @throws SecurityException
	 */
    void delete(DomainExample example) throws SecurityException;

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
    void add(DomainVO vo) throws SecurityException;
    
    /**
     * 读取数据
     * @return
     * @throws SecurityException
     */
    List<Domain> findAll() throws SecurityException;

    /**
     * 读取数据
     * @param example
     * @return
     * @throws SecurityException
     */
    List<Domain> findAll(DomainExample example) throws SecurityException;

    /**
     * 读取一条数据
     * @param id
     * @return
     * @throws SecurityException
     */
    Domain findOne(Long id) throws SecurityException;
    
    /**
     * 获取数据集合
     * @param example
     * @return
     * @throws SecurityException
     */
    List<Domain> findAllWithCates(DomainExample example) throws SecurityException;

    /**
     * 按条件更新
     * @param record
     * @param example
     * @throws SecurityException
     */
    void update(Domain record, DomainExample example) throws SecurityException;

    /**
     * 更新
     * @param id
     * @param vo
     * @throws SecurityException
     */
    void update(Long id, DomainVO vo) throws SecurityException;
    
    /**
     * 更新统计
     * @param id
     * @param count
     * @throws SecurityException
     */
    void update(Long id, int count) throws SecurityException;
    
    /**
     * 验证属性值是否存在
     * @param attr name or title
     * @param value 需要验证的值
     * @return
     * @throws SecurityException
     */
	boolean validate(Attribute attr, String value) throws SecurityException;
	
}
