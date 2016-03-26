package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Attribute;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.front.vo.BookmarkVO;

public interface BookmarkService extends Pager<Bookmark, BookmarkExample> {
	
	/**
	 * 按条件统计
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
	int count(BookmarkExample example) throws SecurityException;

	/**
	 * 按条件删除
	 * @param example
	 * @throws SecurityException
	 */
	void delete(BookmarkExample example) throws SecurityException;

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
	void add(BookmarkVO vo) throws SecurityException;

	/**
	 * 读取数据
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
	List<Bookmark> findAll(BookmarkExample example) throws SecurityException;

	/**
	 * 读取一条数据
	 * @param id
	 * @return
	 * @throws SecurityException
	 */
	Bookmark findOne(Long id) throws SecurityException;
	
	/**
	 * 按条件更新
	 * @param record
	 * @param example
	 * @throws SecurityException
	 */
	void update(Bookmark record, BookmarkExample example) throws SecurityException;

	/**
	 * 按ID更新
	 * @param id
	 * @param vo
	 * @throws SecurityException
	 */
	void update(Long id, BookmarkVO vo) throws SecurityException;
	
	/**
	 * 更新点击，并获取返回URL
	 * @param id
	 * @return
	 * @throws SecurityException
	 */
	String updateHit(Long id) throws SecurityException;
	
	/**
	 * 关注站点
	 * @param fid
	 * @throws SecurityException
	 */
	void follow(Long fid) throws SecurityException;
	
	/**
	 * 取消关注站点
	 * @param fid
	 * @throws SecurityException
	 */
	void unfollow(Long fid) throws SecurityException;

	/**
	 * 验证属性/值是否存在
	 * @param name
	 * @return
	 */
	boolean validate(Attribute attr, String value) throws SecurityException;
	
	/**
	 * 检测是否已关注
	 * @param fid
	 * @return
	 * @throws SecurityException
	 */
	boolean validateFollow(Long fid) throws SecurityException;
	
}
