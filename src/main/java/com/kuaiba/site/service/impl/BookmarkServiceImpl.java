package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.GlobalIds;
import com.kuaiba.site.core.exceptions.CheckedException;
import com.kuaiba.site.core.exceptions.ExceptionIds;
import com.kuaiba.site.core.exceptions.LogicException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.core.utils.HttpUtils;
import com.kuaiba.site.core.utils.LogUtils;
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
		try {
			ValidUtils.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Bookmark> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public int countByExample(BookmarkExample example) {
		try {
			ValidUtils.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void deleteByExample(BookmarkExample example) {
		try {
			ValidUtils.checkNotNull(example);
			mapper.deleteByExample(example);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		try {
			ValidUtils.checkPrimaryKey(id);
			mapper.deleteByPrimaryKey(id);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void add(BookmarkVO vo) {
		try {
			ValidUtils.checkNotNull(vo);
			Bookmark record = new Bookmark();
			record.setName(vo.getNameUUID());
			record.setTitle(vo.getTitle());
			record.setUrl(vo.getUrl());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setCreateBy(CurrentUser.getCurrentUserId());
			record.setCategory(vo.getCategory());
			record.setHit(RandUtils.getRandomHit());
			record.setReffer(GlobalIds.REFFER);
			mapper.insert(record);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_ADD);
		}
	}

	@Override
	public List<Bookmark> findByExample(BookmarkExample example) {
		try {
			ValidUtils.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public Bookmark findByPrimaryKey(Long id) {
		try {
			ValidUtils.checkPrimaryKey(id);
			return mapper.selectByPrimaryKey(id);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void updateByExample(Bookmark record, BookmarkExample example) {
		try {
			ValidUtils.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, BookmarkVO vo) {
		try {
			ValidUtils.checkNotNull(vo);
			ValidUtils.checkPrimaryKey(id);
			Bookmark record = new Bookmark();
			record.setId(id);
			record.setTitle(vo.getTitle());
			record.setUrl(vo.getUrl());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setReffer(vo.getReffer());
			record.setCategory(vo.getCategory());
			mapper.updateByPrimaryKey(record);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

	@Override
	public void unfollow(Long fid) {
		try {
			ValidUtils.checkPrimaryKey(fid);
			bfMapper.deleteByPrimaryKey(CurrentUser.getCurrentUserId(), fid);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_UNFOLLOW);
		}
	}

	@Override
	public void follow(Long fid) {
		try {
			ValidUtils.checkPrimaryKey(fid);
			bfMapper.insert(CurrentUser.getCurrentUserId(), fid);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_UNFOLLOW);
		}
	}

	@Override
	public String hit(Long id) {
		try {
			ValidUtils.checkPrimaryKey(id);
			Bookmark record = findByPrimaryKey(id);
			record.setHit(record.getHit() + 1);
			mapper.updateByPrimaryKey(record);
			return HttpUtils.appendQueryParams(record.getUrl(), record.getReffer());
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

	@Override
	public boolean isFollow(Long fid) {
		try {
			ValidUtils.checkPrimaryKey(fid);
			List<Long> list = bfMapper.selectByUid(CurrentUser.getCurrentUserId());
			return list.contains(fid);
		} catch (CheckedException e) {
			LogUtils.info(logger, e);
			throw e;
		} catch (Exception e) {
			LogUtils.info(logger, e);
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}
	
}
