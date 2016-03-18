package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Pager;
import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.db.entity.TrackExample;

public interface TrackService extends Pager<Track, TrackExample> {

	int countByExample(TrackExample example) throws SecurityException;

	void deleteByExample(TrackExample example) throws SecurityException;

	void deleteByPrimaryKey(Long id) throws SecurityException;
	
	void deleteByPrimaryKey(String[] ids) throws SecurityException;

	void add(Track record) throws SecurityException;

	List<Track> findByExample(TrackExample example) throws SecurityException;

	Track findByPrimaryKey(Long id) throws SecurityException;

	void updateByExample(Track record, TrackExample example) throws SecurityException;

	void updateByPrimaryKey(Long id, Track record) throws SecurityException;

}
