package com.hoti.site.service;

import java.util.List;

import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Pager;
import com.hoti.site.db.entity.Track;
import com.hoti.site.db.entity.TrackExample;

public interface TrackService extends Pager<Track, TrackExample> {

	/**
	 * 按条件统计
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
	int count(TrackExample example) throws SecurityException;

	/**
	 * 按条件删除
	 * @param example
	 * @throws SecurityException
	 */
	void delete(TrackExample example) throws SecurityException;

	/**
	 * 删除
	 * @param id
	 * @throws SecurityException
	 */
	void delete(Long id) throws SecurityException;
	
	/**
	 * 批量删除
	 * @param ids
	 * @throws SecurityException
	 */
	void delete(String[] ids) throws SecurityException;

	/**
	 * 添加
	 * @param record
	 * @throws SecurityException
	 */
	void add(Track record) throws SecurityException;

	/**
	 * 读取数据列表
	 * @param example
	 * @return
	 * @throws SecurityException
	 */
	List<Track> findAll(TrackExample example) throws SecurityException;

	/**
	 * 读取数据
	 * @param id
	 * @return
	 * @throws SecurityException
	 */
	Track findOne(Long id) throws SecurityException;

	/**
	 * 按条件更新
	 * @param record
	 * @param example
	 * @throws SecurityException
	 */
	void update(Track record, TrackExample example) throws SecurityException;

	/**
	 * 更新
	 * @param id
	 * @param record
	 * @throws SecurityException
	 */
	void update(Long id, Track record) throws SecurityException;

}
