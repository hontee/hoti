package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Website;
import com.kuaiba.site.db.entity.WebsiteExample;
import com.kuaiba.site.front.vo.WebsiteVO;
import com.kuaiba.site.service.kit.Pager;

public interface WebsiteService extends Pager<Website, WebsiteExample> {
	
	int countByExample(WebsiteExample example);

	void deleteByExample(WebsiteExample example);

	void deleteByPrimaryKey(Long id);

	void add(WebsiteVO vo);

	List<Website> findByExample(WebsiteExample example);

	Website findByPrimaryKey(Long id);

	void updateByExample(Website record, WebsiteExample example);

	void updateByPrimaryKey(Long id, WebsiteVO vo);
	
	void unfollow(Long uid, Long fid);

	void follow(Long uid, Long fid);
	
	boolean isFollow(Long fid);
	
	String hit(Long id);
	
}
