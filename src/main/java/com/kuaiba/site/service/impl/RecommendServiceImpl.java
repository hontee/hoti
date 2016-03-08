package com.kuaiba.site.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.db.dao.RecommendMapper;
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.exceptions.BType;
import com.kuaiba.site.exceptions.BusinessException;
import com.kuaiba.site.front.vo.RecommendVO;
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.security.Administrator;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.service.BookmarkService;
import com.kuaiba.site.service.kit.Pagination;
import com.kuaiba.site.service.kit.ValidUtils;
import com.kuaiba.site.utils.FetchUtils;
import com.kuaiba.site.utils.FetchUtils.WebModel;

@Service
public class RecommendServiceImpl implements RecommendService {
	
	private Logger logger = LoggerFactory.getLogger(RecommendServiceImpl.class);
	
	@Resource
	private RecommendMapper mapper;
	
	@Resource
	private BookmarkService webService;

	@Override
	public PageInfo<Recommend> findByExample(RecommendExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Recommend> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public int countByExample(RecommendExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void deleteByExample(RecommendExample example) {
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
	public void add(String url) {
		ValidUtils.checkNotNull(url);
		try {
			WebModel wm = FetchUtils.connect(url);
			Recommend record = new Recommend();
			if (Administrator.isLogin()) {
				record.setCreator(Administrator.getName());
			}
			record.setDescription(wm.getDescription());
			record.setName(UUID.randomUUID().toString());
			record.setState((byte)1); // 待审核
			record.setTitle(wm.getTitle());
			record.setUrl(url);
			record.setKeywords(wm.getKeywords());
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}		
	}

	@Override
	public List<Recommend> findByExample(RecommendExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public Recommend findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void updateByExample(Recommend record, RecommendExample example) {
		ValidUtils.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, RecommendVO vo) {
		ValidUtils.checkNotNull(vo);
		ValidUtils.checkPrimaryKey(id);
		try {
			Recommend record = new Recommend();
			record.setId(id);
			record.setDescription(vo.getDescription());
			record.setTitle(vo.getTitle());
			record.setKeywords(vo.getKeywords());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void audit(Long id, String remark) {
		ValidUtils.checkNotNull(remark);
		ValidUtils.checkPrimaryKey(id);
		try {
			Recommend record = new Recommend();
			record.setId(id);
			record.setState((byte)3); // 审核拒绝
			record.setRemark(remark);
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void audit(Long id, BookmarkVO vo) {
		ValidUtils.checkNotNull(vo);
		ValidUtils.checkPrimaryKey(id);
		try {
			Recommend record = new Recommend();
			record.setId(id);
			record.setState((byte)2); // 审核通过
			mapper.updateByPrimaryKey(record);
			webService.add(vo);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

}
