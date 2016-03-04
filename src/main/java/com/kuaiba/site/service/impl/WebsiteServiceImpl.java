package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.GlobalIds;
import com.kuaiba.site.db.dao.BookmarkFollowMapper;
import com.kuaiba.site.db.dao.WebsiteMapper;
import com.kuaiba.site.db.entity.Website;
import com.kuaiba.site.db.entity.WebsiteExample;
import com.kuaiba.site.exceptions.BType;
import com.kuaiba.site.exceptions.BusinessException;
import com.kuaiba.site.front.vo.WebsiteVO;
import com.kuaiba.site.net.HttpUtils;
import com.kuaiba.site.security.Administrator;
import com.kuaiba.site.service.WebsiteService;
import com.kuaiba.site.service.kit.Pagination;
import com.kuaiba.site.service.kit.RandomKit;
import com.kuaiba.site.service.kit.ValidKit;

@Service
public class WebsiteServiceImpl implements WebsiteService {
	
	private Logger logger = LoggerFactory.getLogger(WebsiteServiceImpl.class);
	
	@Resource
	private WebsiteMapper mapper;
	
	@Resource
	private BookmarkFollowMapper bfMapper;

	@Override
	public PageInfo<Website> findByExample(WebsiteExample example, Pagination p) {
		ValidKit.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Website> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public int countByExample(WebsiteExample example) {
		ValidKit.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void deleteByExample(WebsiteExample example) {
		ValidKit.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidKit.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void add(WebsiteVO vo) {
		ValidKit.checkNotNull(vo);
		try {
			Website record = new Website();
			record.setName(vo.getNameUUID());
			record.setTitle(vo.getTitle());
			record.setUrl(vo.getUrl());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setCreateBy(Administrator.getId());
			record.setCategory(vo.getCategory());
			record.setHit(RandomKit.getRandomHit());
			record.setReffer(GlobalIds.REFFER);
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public List<Website> findByExample(WebsiteExample example) {
		ValidKit.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public Website findByPrimaryKey(Long id) {
		ValidKit.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void updateByExample(Website record, WebsiteExample example) {
		ValidKit.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, WebsiteVO vo) {
		ValidKit.checkNotNull(vo);
		ValidKit.checkPrimaryKey(id);
		try {
			Website record = new Website();
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
	public void unfollow(Long uid, Long fid) {
		ValidKit.checkPrimaryKey(uid);
		ValidKit.checkPrimaryKey(fid);
		try {
			bfMapper.deleteByPrimaryKey(uid, fid);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void follow(Long uid, Long fid) {
		ValidKit.checkPrimaryKey(uid);
		ValidKit.checkPrimaryKey(fid);
		try {
			bfMapper.insert(uid, fid);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public String hit(Long id) {
		ValidKit.checkPrimaryKey(id);
		try {
			Website record = findByPrimaryKey(id);
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
		ValidKit.checkPrimaryKey(fid);
		try {
			List<Long> list = bfMapper.selectByUid(Administrator.getId());
			return list.contains(fid);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			return false;
		}
	}
	
}
