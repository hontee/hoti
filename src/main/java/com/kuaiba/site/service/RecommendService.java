package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.front.vo.RecommendVO;
import com.kuaiba.site.front.vo.BookmarkVO;

public interface RecommendService extends Pager<Recommend, RecommendExample> {
	
	int countByExample(RecommendExample example) throws SecurityException;

    void deleteByExample(RecommendExample example) throws SecurityException;

    void deleteByPrimaryKey(Long id) throws SecurityException;

    void add(String url) throws SecurityException;

    List<Recommend> findByExample(RecommendExample example) throws SecurityException;

    Recommend findByPrimaryKey(Long id) throws SecurityException;

    void updateByExample(Recommend record, RecommendExample example) throws SecurityException;

    void updateByPrimaryKey(Long id, RecommendVO vo) throws SecurityException;
    
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
