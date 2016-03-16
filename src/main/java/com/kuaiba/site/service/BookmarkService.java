package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.front.vo.BookmarkVO;

public interface BookmarkService extends Pager<Bookmark, BookmarkExample> {
	
	int countByExample(BookmarkExample example) throws SecurityException;

	void deleteByExample(BookmarkExample example) throws SecurityException;

	void deleteByPrimaryKey(Long id) throws SecurityException;

	void add(BookmarkVO vo) throws SecurityException;

	List<Bookmark> findByExample(BookmarkExample example) throws SecurityException;

	Bookmark findByPrimaryKey(Long id) throws SecurityException;

	void updateByExample(Bookmark record, BookmarkExample example) throws SecurityException;

	void updateByPrimaryKey(Long id, BookmarkVO vo) throws SecurityException;
	
	void unfollow(Long fid) throws SecurityException;

	void follow(Long fid) throws SecurityException;
	
	boolean isFollow(Long fid) throws SecurityException;
	
	String hit(Long id) throws SecurityException;
	
	/**
	 * 验证Bookmark名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkBookmarkName(String name) throws SecurityException;
	
	/**
	 * 验证BookmarkURL是否存在
	 * @param url
	 * @return
	 */
	boolean checkBookmarkURL(String url) throws SecurityException;
	
	/**
	 * 验证Bookmark标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkBookmarkTitle(String title) throws SecurityException;
	
}
