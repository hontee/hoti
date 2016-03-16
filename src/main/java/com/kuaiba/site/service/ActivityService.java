package com.kuaiba.site.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Activity;
import com.kuaiba.site.db.entity.ActivityExample;
import com.kuaiba.site.db.entity.Pager;

public interface ActivityService extends Pager<Activity, ActivityExample> {

	int countByExample(ActivityExample example) throws SecurityException;

	void deleteByExample(ActivityExample example) throws SecurityException;

	void deleteByPrimaryKey(Long id) throws SecurityException;

	void add(Activity record) throws SecurityException;
	
	void logger(String action, String tbl, String desc, Byte state, HttpServletRequest request) throws SecurityException;

	List<Activity> findByExample(ActivityExample example) throws SecurityException;

	Activity findByPrimaryKey(Long id) throws SecurityException;
}
