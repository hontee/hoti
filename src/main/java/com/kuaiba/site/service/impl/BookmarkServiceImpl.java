package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.FollowException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UnfollowException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.core.exception.ValidationException;
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.db.dao.BookmarkFollowMapper;
import com.kuaiba.site.db.dao.BookmarkMapper;
import com.kuaiba.site.db.entity.Attribute;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.GlobalIDs;
import com.kuaiba.site.db.entity.HttpUtil;
import com.kuaiba.site.db.entity.PagerUtil;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.VUtil;
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.interceptor.ClearCache;
import com.kuaiba.site.service.BookmarkService;
import com.kuaiba.site.service.CachePolicy;

@Service
public class BookmarkServiceImpl implements BookmarkService {
	
	@Resource
	private BookmarkMapper mapper;
	@Resource
	private BookmarkFollowMapper bfMapper;
	@Resource
	private CachePolicy cacheMgr;

	@Override
	public PageInfo<Bookmark> find(BookmarkExample example, Pagination p) throws SecurityException {
		try {
			VUtil.assertNotNull(example, p);
			PagerUtil.startPage(p);
			List<Bookmark> list = findAll(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取站点失败", e);
		}
	}

	@Override
	public int count(BookmarkExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计站点失败", e);
		}
	}
	
	

	@Override
	public int count(String title) throws SecurityException {
		BookmarkExample example = new BookmarkExample();
		BookmarkExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotEmpty(title)) {
			criteria.andTitleLike("%" + title + "%"); // 模糊查询
		}
		return count(example);
	}

	@Override
	public void delete(BookmarkExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除站点失败", e);
		}
	}

	@Override
	public void delete(Long id) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除站点失败", e);
		}
	}

	@Override
	public void add(BookmarkVO vo) throws SecurityException { 
		try {
			VUtil.assertNotNull(vo);
			Bookmark record = new Bookmark();
			record.setName(vo.getNameUUID());
			record.setTitle(vo.getTitle());
			record.setUrl(vo.getUrl());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setCreateBy(AuthzUtil.getUserId());
			record.setCategory(vo.getCategory());
			record.setHitRandom();
			record.setReffer(GlobalIDs.REFFER);
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加站点失败", e);
		}
	}

	@Override
	public List<Bookmark> findAll(BookmarkExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			List<Bookmark> list = mapper.selectByExample(example);
			for (Bookmark bm : list) {
				bm.setMt(cacheMgr.readMtype(bm.getMtype()));
			}
			return list;
		} catch (Exception e) {
			throw new ReadException("读取站点失败", e);
		}
	}

	@Override
	public Bookmark findOne(Long id) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取站点失败", e);
		}
	}

	@Override
	public void update(Bookmark record, BookmarkExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新站点失败", e);
		}
	}

	@Override
	public void update(Long id, BookmarkVO vo) throws SecurityException { 
		try {
			VUtil.assertNotNull(vo, id);
			Bookmark record = new Bookmark();
			record.setId(id);
			record.setTitle(vo.getTitle());
			record.setUrl(vo.getUrl());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setReffer(vo.getReffer());
			record.setCategory(vo.getCategory());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新站点失败", e);
		}
	}
	
	@Override
	public String updateHit(Long id) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			Bookmark record = findOne(id);
			record.setHit(record.getHit() + 1);
			mapper.updateByPrimaryKey(record);
			return HttpUtil.appendQueryParams(record.getUrl(), record.getReffer());
		} catch (Exception e) {
			throw new ReadException("点击获取URL失败", e);
		}
	}
	
	@Override
	@ClearCache("user_follow_bms")
	public void follow(Long fid) throws SecurityException { 
		try {
			VUtil.assertNotNull(fid);
			bfMapper.insert(AuthzUtil.getUserId(), fid);
		} catch (Exception e) {
			throw new FollowException("关注站点失败", e);
		}
	}

	@Override
	@ClearCache("user_follow_bms")
	public void unfollow(Long fid) throws SecurityException { 
		try {
			VUtil.assertNotNull(fid);
			bfMapper.delete(AuthzUtil.getUserId(), fid);
		} catch (Exception e) {
			throw new UnfollowException("取消关注站点失败", e);
		}
	}

	@Override
	public boolean validate(Attribute attr, String value) throws SecurityException {
		try {
			VUtil.assertNotNull(value);
			BookmarkExample example = new BookmarkExample();
			
			if (attr == Attribute.TITLE) {
				example.createCriteria().andTitleEqualTo(value);
			} else if (attr == Attribute.URL) {
				example.createCriteria().andUrlEqualTo(value);
			} else { // Attribute.NAME
				example.createCriteria().andNameEqualTo(value);
			}
			
			return !mapper.selectByExample(example).isEmpty();
		} catch (Exception e) {
			throw new ValidationException("验证站点" + attr.name() + "失败", e);
		}
	}
	
	@Override
	public boolean validateFollow(Long fid) throws SecurityException { 
		try {
			VUtil.assertNotNull(fid);
			return cacheMgr.readUserFollowBMS().contains(fid);
		} catch (Exception e) {
			throw new ReadException("判断用户是否关注站点失败", e);
		}
	}
	
}
