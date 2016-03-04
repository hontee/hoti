package com.kuaiba.site.db.dao;

import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookmarkMapper {
	
	int countByExample(BookmarkExample example);

	int deleteByExample(BookmarkExample example);

	int deleteByPrimaryKey(Long id);

	int insert(Bookmark record);

	List<Bookmark> selectByExample(BookmarkExample example);

	Bookmark selectByPrimaryKey(Long id);
	
	List<Bookmark> selectByCategory(@Param("category") Long category);

	int updateByExample(@Param("record") Bookmark record, @Param("example") BookmarkExample example);

	int updateByPrimaryKey(Bookmark record);
	
}