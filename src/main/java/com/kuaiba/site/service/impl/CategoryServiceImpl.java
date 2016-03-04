package com.kuaiba.site.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.db.dao.BookmarkFollowMapper;
import com.kuaiba.site.db.dao.CategoryMapper;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.exceptions.BType;
import com.kuaiba.site.exceptions.BusinessException;
import com.kuaiba.site.front.vo.CategoryVO;
import com.kuaiba.site.security.Administrator;
import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.service.kit.Pagination;
import com.kuaiba.site.service.kit.ValidUtils;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Resource
	private BookmarkFollowMapper bfMapper;
	
	@Resource
	private CategoryMapper mapper;

	@Override
	public PageInfo<Category> findByExample(CategoryExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Category> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public int countByExample(CategoryExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void deleteByExample(CategoryExample example) {
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
	public void add(CategoryVO vo) {
		ValidUtils.checkNotNull(vo);
		try {
			Category record = new Category();
			record.setName(vo.getName());
			record.setTitle(vo.getTitle());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setDomain(vo.getDomain());
			record.setCreateBy(Administrator.getId());
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public List<Category> findByExample(CategoryExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public Category findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void updateByExample(Category record, CategoryExample example) {
		ValidUtils.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, CategoryVO vo) {
		ValidUtils.checkNotNull(vo);
		ValidUtils.checkPrimaryKey(id);
		try {
			Category record = new Category();
			record.setId(id);
			record.setName(vo.getName());
			record.setTitle(vo.getTitle());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setDomain(vo.getDomain());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public List<Category> findByOrganization(Long domain) {
		ValidUtils.checkNotNull(domain);
		try {
			return mapper.selectByDomain(domain);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}

	@Override
	public List<Category> findByCollect(CategoryExample example) {
		ValidUtils.checkNotNull(example);
		try {
			List<Category> cates = mapper.selectByCollect(example);
			List<Category> list = new ArrayList<>();
			Long uid = Administrator.isLogin() ? Administrator.getId(): 0L;
			final List<Long> fids = bfMapper.selectByUid(uid);
			
			cates.forEach((c) -> {
				List<Bookmark> ws = c.getBookmarks();
				if (ws.isEmpty()) {
					list.add(c);
				} else {
					ws.forEach((web) -> {
						if (fids.contains(web.getId())) {
							web.setExtFollow(1);
						}
					});
				}
			});
			cates.removeAll(list);
			return cates;
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(BType.KB2003);
		}
	}
	
}
