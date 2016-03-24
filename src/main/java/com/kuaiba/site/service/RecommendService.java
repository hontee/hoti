package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.front.vo.RecommendVO;

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
    List<Recommend> read(RecommendExample example) throws SecurityException;

    /**
     * 读取数据
     * @param id
     * @return
     * @throws SecurityException
     */
    Recommend read(Long id) throws SecurityException;

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

}
