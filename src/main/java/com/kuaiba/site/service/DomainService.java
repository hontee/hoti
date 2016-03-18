package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.front.vo.DomainVO;

public interface DomainService extends Pager<Domain, DomainExample> {

	int countByExample(DomainExample example) throws SecurityException;

    void deleteByExample(DomainExample example) throws SecurityException;

    void deleteByPrimaryKey(Long id) throws SecurityException;

    void add(DomainVO vo) throws SecurityException;

    List<Domain> findByExample(DomainExample example) throws SecurityException;

    Domain findByPrimaryKey(Long id) throws SecurityException;
    
    List<Domain> findByCollect(DomainExample example) throws SecurityException;

    void updateByExample(Domain record, DomainExample example) throws SecurityException;

    void updateByPrimaryKey(Long id, DomainVO vo) throws SecurityException;
    
    void updateByPrimaryKey(Long id, int count) throws SecurityException;
    
    /**
	 * 验证Domain名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkDomainName(String name) throws SecurityException;
	
	/**
	 * 验证Domain标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkDomainTitle(String title) throws SecurityException;
    
}
