package com.hoti.site.service;

import java.util.List;

import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Pager;
import com.hoti.site.db.entity.Recommend;
import com.hoti.site.db.entity.RecommendExample;
import com.hoti.site.front.vo.BookmarkVO;
import com.hoti.site.front.vo.RecommendVO;

public interface RecommendService extends Pager<Recommend, RecommendExample> {
	
	/**
	 * 按条件统计
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
	int count(RecommendExample example) throws SecurityException;

	/**
	 * 按条件删除
	 * @param example
	 * @throws SecurityException
	 */
    void delete(RecommendExample example) throws SecurityException;

    /**
     * 删除
     * @param id
     * @throws SecurityException
     */
    void delete(Long id) throws SecurityException;

    /**
     * 添加
     * @param url
     * @throws SecurityException
     */
    void add(String url) throws SecurityException;

    /**
     * 读取数据列表
     * @param example
     * @return
     * @throws SecurityException
     */
    List<Recommend> findAll(RecommendExample example) throws SecurityException;

    /**
     * 读取数据
     * @param id
     * @return
     * @throws SecurityException
     */
    Recommend findOne(Long id) throws SecurityException;

    /**
     * 按条件更新
     * @param record
     * @param example
     * @throws SecurityException
     */
    void update(Recommend record, RecommendExample example) throws SecurityException;

    /**
     * 更新
     * @param id
     * @param vo
     * @throws SecurityException
     */
    void update(Long id, RecommendVO vo) throws SecurityException;
    
    /**
     * 审核拒绝
     * @param id
     * @param remark
     */
    void audit(Long id, String remark) throws SecurityException;
    
    /**
     * 审核通过
     * @param id
     * @param remark
     */
    void audit(Long id, BookmarkVO vo) throws SecurityException;

}
