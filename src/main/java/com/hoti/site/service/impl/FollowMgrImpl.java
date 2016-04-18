package com.hoti.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.ReadException;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.dao.BookmarkMapper;
import com.hoti.site.db.dao.FollowUserMapper;
import com.hoti.site.db.entity.Bookmark;
import com.hoti.site.db.entity.BookmarkExample;
import com.hoti.site.db.entity.FollowUser;
import com.hoti.site.db.entity.FollowUserExample;
import com.hoti.site.db.entity.PagerUtil;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.VUtil;
import com.hoti.site.service.Followable;

@Service
public class FollowMgrImpl implements Followable {
	
	@Resource
	private FollowUserMapper bfu;
	@Resource
	private BookmarkMapper bm;

	@Override
	public int countBmfUser(FollowUserExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			return bfu.countByBookmark(example);
		} catch (Exception e) {
			throw new ReadException("统计站点被关注的用户失败", e);
		}
	}

	@Override
	public PageInfo<FollowUser> findBmfUser(FollowUserExample example, Pagination p) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			PagerUtil.startPage(p);
			List<FollowUser> list = bfu.selectByBookmark(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("查询站点被关注的用户列表失败", e);
		}
	}

	@Override
	public PageInfo<Bookmark> findGBRelation(BookmarkExample example, Pagination p)
			throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			PagerUtil.startPage(p);
			List<Bookmark> list = bm.selectByRelation(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("查询群组管理的站点列表失败", e);
		}
	}

	@Override
	public int countGroupUser(FollowUserExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			return bfu.countByGroup(example);
		} catch (Exception e) {
			throw new ReadException("统计群组被关注的用户失败", e);
		}
	}

	@Override
	public PageInfo<FollowUser> findGroupUser(FollowUserExample example, Pagination p) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			PagerUtil.startPage(p);
			List<FollowUser> list = bfu.selectByGroup(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("查询群组被关注的用户列表失败", e);
		}
	}

}
