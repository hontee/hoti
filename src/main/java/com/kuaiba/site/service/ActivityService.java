package com.kuaiba.site.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kuaiba.site.db.entity.Activity;
import com.kuaiba.site.db.entity.ActivityExample;
import com.kuaiba.site.service.utils.Pager;

public interface ActivityService extends Pager<Activity, ActivityExample> {

	int countByExample(ActivityExample example);

	void deleteByExample(ActivityExample example);

	void deleteByPrimaryKey(Long id);

	void add(Activity record);
	
	void logger(String action, String tbl, String desc, HttpServletRequest request);

	List<Activity> findByExample(ActivityExample example);

	Activity findByPrimaryKey(Long id);
}
