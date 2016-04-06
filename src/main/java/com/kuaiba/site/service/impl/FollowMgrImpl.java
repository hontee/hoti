package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.dao.FollowUserMapper;
import com.kuaiba.site.db.dao.GroupBookmarkRelationMapper;
import com.kuaiba.site.db.entity.FollowUser;
import com.kuaiba.site.db.entity.FollowUserExample;
import com.kuaiba.site.db.entity.GroupBookmarkRelation;
import com.kuaiba.site.db.entity.GroupBookmarkRelationExample;
import com.kuaiba.site.db.entity.PagerUtil;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.VUtil;
import com.kuaiba.site.service.Followable;

@Service
public class FollowMgrImpl implements Followable {
	
	@Resource
	private FollowUserMapper bfu;
	@Resource
	private GroupBookmarkRelationMapper gbr;

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
	public int countGBRelation(GroupBookmarkRelationExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			return gbr.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计群组管理的站点数失败", e);
		}
	}

	@Override
	public PageInfo<GroupBookmarkRelation> findGBRelation(GroupBookmarkRelationExample example, Pagination p)
			throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			PagerUtil.startPage(p);
			List<GroupBookmarkRelation> list = gbr.selectByExample(example);
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
