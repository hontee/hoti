package com.kuaiba.site.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;

public interface BookmarkMapper {
	
	/**
	 * 统计
	 * @param example
	 * @return
	 */
	int countByExample(BookmarkExample example);

	/**
	 * 删除
	 * @param example
	 * @return
	 */
	int deleteByExample(BookmarkExample example);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 添加
	 * @param record
	 * @return
	 */
	int insert(Bookmark record);

	/**
	 * 查询
	 * @param example
	 * @return
	 */
	List<Bookmark> selectByExample(BookmarkExample example);
	
	/**
	 * 查询含主题ID
	 * @param example
	 * @return
	 */
	List<Bookmark> selectByRelation(BookmarkExample example);

	/**
	 * 查询
	 * @param id
	 * @return
	 */
	Bookmark selectByPrimaryKey(Long id);
	
	/**
	 * 更新
	 * @param record
	 * @param example
	 * @return
	 */
	int updateByExample(@Param("record") Bookmark record, @Param("example") BookmarkExample example);

	/**
	 * 更新
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Bookmark record);
	
}