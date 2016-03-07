package com.kuaiba.site.service;

import java.util.List;

import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.db.entity.TrackExample;
import com.kuaiba.site.service.kit.Pager;

public interface TrackService extends Pager<Track, TrackExample> {

	int countByExample(TrackExample example);

	void deleteByExample(TrackExample example);

	void deleteByPrimaryKey(Long id);

	void add(Track record);

	List<Track> findByExample(TrackExample example);

	Track findByPrimaryKey(Long id);

	void updateByExample(Track record, TrackExample example);

	void updateByPrimaryKey(Long id, Track record);

}
