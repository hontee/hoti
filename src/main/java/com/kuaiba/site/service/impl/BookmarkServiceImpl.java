package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.GlobalIDs;
import com.kuaiba.site.core.LogUtils;
import com.kuaiba.site.core.exceptions.CheckedException;
import com.kuaiba.site.core.exceptions.ExceptionIds;
import com.kuaiba.site.core.exceptions.LogicException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.dao.BookmarkFollowMapper;
import com.kuaiba.site.db.dao.BookmarkMapper;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.HttpUtil;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.service.BookmarkService;

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
			ContraintValidator.checkNotNull(example, p);
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
			ContraintValidator.checkNotNull(example);
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
			ContraintValidator.checkNotNull(example);
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
			ContraintValidator.checkPrimaryKey(id);
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
			ContraintValidator.checkNotNull(vo);
			Bookmark record = new Bookmark();
			record.setName(vo.getNameUUID());
			record.setTitle(vo.getTitle());
			record.setUrl(vo.getUrl());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setCreateBy(CurrentUser.getCurrentUserId());
			record.setCategory(vo.getCategory());
			record.setHitRandom();
			record.setReffer(GlobalIDs.REFFER);
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
			ContraintValidator.checkNotNull(example);
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
			ContraintValidator.checkPrimaryKey(id);
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
			ContraintValidator.checkNotNull(record, example);
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
			ContraintValidator.checkNotNull(vo);
			ContraintValidator.checkPrimaryKey(id);
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
			ContraintValidator.checkPrimaryKey(fid);
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
			ContraintValidator.checkPrimaryKey(fid);
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
			ContraintValidator.checkPrimaryKey(id);
			Bookmark record = findByPrimaryKey(id);
			record.setHit(record.getHit() + 1);
			mapper.updateByPrimaryKey(record);
			return HttpUtil.appendQueryParams(record.getUrl(), record.getReffer());
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
			ContraintValidator.checkPrimaryKey(fid);
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
	
	@Override
	public boolean checkBookmarkName(String name) {
		ContraintValidator.checkNotNull(name);
		try {
			BookmarkExample example = new BookmarkExample();
			example.createCriteria().andNameEqualTo(name);
			List<Bookmark> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkBookmarkURL(String url) {
		ContraintValidator.checkNotNull(url);
		try {
			BookmarkExample example = new BookmarkExample();
			example.createCriteria().andUrlEqualTo(url);
			List<Bookmark> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean checkBookmarkTitle(String title) {
		ContraintValidator.checkNotNull(title);
		try {
			BookmarkExample example = new BookmarkExample();
			example.createCriteria().andTitleEqualTo(title);
			List<Bookmark> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
		}
		return false;
	}
	
}
