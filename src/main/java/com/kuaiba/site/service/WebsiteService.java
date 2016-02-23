package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Website;
import com.kuaiba.site.db.entity.WebsiteExample;
import com.kuaiba.site.support.Pager;

public interface WebsiteService extends Pager<Website, WebsiteExample> {
	
	int countByExample(WebsiteExample example);

	void deleteByExample(WebsiteExample example);

	void deleteByPrimaryKey(Long id);

	void add(Website record);

	List<Website> findByExample(WebsiteExample example);

	Website findByPrimaryKey(Long id);

	void updateByExample(Website record, WebsiteExample example);

	void updateByPrimaryKey(Website record);
	
	void unfollow(Long uid, Long fid);

	void follow(Long uid, Long fid);

}
