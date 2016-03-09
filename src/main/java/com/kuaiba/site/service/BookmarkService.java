package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.service.utils.Pager;

public interface BookmarkService extends Pager<Bookmark, BookmarkExample> {
	
	int countByExample(BookmarkExample example);

	void deleteByExample(BookmarkExample example);

	void deleteByPrimaryKey(Long id);

	void add(BookmarkVO vo);

	List<Bookmark> findByExample(BookmarkExample example);

	Bookmark findByPrimaryKey(Long id);

	void updateByExample(Bookmark record, BookmarkExample example);

	void updateByPrimaryKey(Long id, BookmarkVO vo);
	
	void unfollow(Long fid);

	void follow(Long fid);
	
	boolean isFollow(Long fid);
	
	String hit(Long id);
	
	/**
	 * 验证Bookmark名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkBookmarkName(String name);
	
	/**
	 * 验证BookmarkURL是否存在
	 * @param url
	 * @return
	 */
	boolean checkBookmarkURL(String url);
	
	/**
	 * 验证Bookmark标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkBookmarkTitle(String title);
	
}
