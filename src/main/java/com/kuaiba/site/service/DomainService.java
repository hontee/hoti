package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.cache.DomainCachePolicy;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.front.vo.DomainVO;

public interface DomainService extends Pager<Domain, DomainExample>, DomainCachePolicy {

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
     * @param example
     * @return
     * @throws SecurityException
     */
    List<Domain> read(DomainExample example) throws SecurityException;

    /**
     * 读取一条数据
     * @param id
     * @return
     * @throws SecurityException
     */
    Domain read(Long id) throws SecurityException;
    
    /**
     * 获取数据集合
     * @param example
     * @return
     * @throws SecurityException
     */
    List<Domain> search(DomainExample example) throws SecurityException;

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
	boolean validate(Domain.Attrs attr, String value) throws SecurityException;
	
}
