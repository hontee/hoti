package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.front.vo.RecommendVO;
import com.kuaiba.site.service.utils.Pager;
import com.kuaiba.site.front.vo.BookmarkVO;

public interface RecommendService extends Pager<Recommend, RecommendExample> {
	
	int countByExample(RecommendExample example);

    void deleteByExample(RecommendExample example);

    void deleteByPrimaryKey(Long id);

    void add(String url);

    List<Recommend> findByExample(RecommendExample example);

    Recommend findByPrimaryKey(Long id);

    void updateByExample(Recommend record, RecommendExample example);

    void updateByPrimaryKey(Long id, RecommendVO vo);
    
    /**
     * 审核拒绝
     * @param id
     * @param remark
     */
    void audit(Long id, String remark);
    
    /**
     * 审核通过
     * @param id
     * @param remark
     */
    void audit(Long id, BookmarkVO vo);

}
