package com.kuaiba.site.db.dao;

import com.kuaiba.site.db.entity.Website;
import com.kuaiba.site.db.entity.WebsiteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebsiteMapper {
	
	int countByExample(WebsiteExample example);

	int deleteByExample(WebsiteExample example);

	int deleteByPrimaryKey(Long id);

	int insert(Website record);

	List<Website> selectByExample(WebsiteExample example);

	Website selectByPrimaryKey(Long id);

	int updateByExample(@Param("record") Website record, @Param("example") WebsiteExample example);

	int updateByPrimaryKey(Website record);
	
}