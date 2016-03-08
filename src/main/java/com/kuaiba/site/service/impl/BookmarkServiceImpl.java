package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.core.GlobalIds;
import com.kuaiba.site.core.exceptions.BType;
import com.kuaiba.site.core.exceptions.BusinessException;
import com.kuaiba.site.core.security.Administrator;
import com.kuaiba.site.core.utils.HttpUtils;
import com.kuaiba.site.db.dao.BookmarkFollowMapper;
import com.kuaiba.site.db.dao.BookmarkMapper;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.service.BookmarkService;
import com.kuaiba.site.service.utils.Pagination;
import com.kuaiba.site.service.utils.RandUtils;
import com.kuaiba.site.service.utils.ValidUtils;

@Service
public class BookmarkServiceImpl implements BookmarkService {
	
	private Logger logger = LoggerFactory.getLogger(BookmarkServiceImpl.class);
	
	@Resource
	private BookmarkMapper mapper;
	
	@Resource
	private BookmarkFollowMapper bfMapper;

	@Override
	public PageInfo<Bookmark> findByExample(BookmarkExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Bookmark> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public int countByExample(BookmarkExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void deleteByExample(BookmarkExample example) {
		ValidUtils.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void add(BookmarkVO vo) {
		ValidUtils.checkNotNull(vo);
		try {
			Bookmark record = new Bookmark();
			record.setName(vo.getNameUUID());
			record.setTitle(vo.getTitle());
			record.setUrl(vo.getUrl());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setCreateBy(Administrator.getId());
			record.setCategory(vo.getCategory());
			record.setHit(RandUtils.getRandomHit());
			record.setReffer(GlobalIds.REFFER);
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public List<Bookmark> findByExample(BookmarkExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public Bookmark findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void updateByExample(Bookmark record, BookmarkExample example) {
		ValidUtils.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, BookmarkVO vo) {
		ValidUtils.checkNotNull(vo);
		ValidUtils.checkPrimaryKey(id);
		try {
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
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void unfollow(Long fid) {
		ValidUtils.checkPrimaryKey(fid);
		try {
			bfMapper.deleteByPrimaryKey(Administrator.getId(), fid);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void follow(Long fid) {
		ValidUtils.checkPrimaryKey(fid);
		try {
			bfMapper.insert(Administrator.getId(), fid);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public String hit(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			Bookmark record = findByPrimaryKey(id);
			record.setHit(record.getHit() + 1);
			mapper.updateByPrimaryKey(record);
			return HttpUtils.appendQueryParams(record.getUrl(), record.getReffer());
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public boolean isFollow(Long fid) {
		ValidUtils.checkPrimaryKey(fid);
		try {
			List<Long> list = bfMapper.selectByUid(Administrator.getId());
			return list.contains(fid);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			return false;
		}
	}
	
}
