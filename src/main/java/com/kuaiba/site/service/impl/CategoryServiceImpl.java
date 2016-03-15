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
import com.kuaiba.site.aop.UseCache;
import com.kuaiba.site.core.cache.CacheIDs;
import com.kuaiba.site.core.exceptions.ExceptionIds;
import com.kuaiba.site.core.exceptions.LogicException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.dao.BookmarkFollowMapper;
import com.kuaiba.site.db.dao.CategoryMapper;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.front.vo.CategoryVO;
import com.kuaiba.site.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Resource
	private BookmarkFollowMapper bfMapper;
	
	@Resource
	private CategoryMapper mapper;

	@Override
	public PageInfo<Category> findByExample(CategoryExample example, Pagination p) {
		ContraintValidator.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Category> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public int countByExample(CategoryExample example) {
		ContraintValidator.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void deleteByExample(CategoryExample example) {
		ContraintValidator.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ContraintValidator.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void add(CategoryVO vo) {
		ContraintValidator.checkNotNull(vo);
		try {
			Category record = new Category();
			record.setName(vo.getName());
			record.setTitle(vo.getTitle());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setDomain(vo.getDomain());
			record.setCreateBy(CurrentUser.getCurrentUserId());
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_ADD);
		}
	}

	@Override
	public List<Category> findByExample(CategoryExample example) {
		ContraintValidator.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public Category findByPrimaryKey(Long id) {
		ContraintValidator.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void updateByExample(Category record, CategoryExample example) {
		ContraintValidator.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, CategoryVO vo) {
		ContraintValidator.checkNotNull(vo);
		ContraintValidator.checkPrimaryKey(id);
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
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

	@Override
	public List<Category> findByOrganization(Long domain) {
		ContraintValidator.checkNotNull(domain);
		try {
			return mapper.selectByDomain(domain);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	@UseCache(key = CacheIDs.HOMEPAGE)
	public List<Category> findByCollect(CategoryExample example) {
		ContraintValidator.checkNotNull(example);
		try {
			List<Category> list = new ArrayList<>();
			List<Category> cates = mapper.selectByCollect(example);
			final List<Long> fids = bfMapper.selectByUid(CurrentUser.getCurrentUserId());
			
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
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}
	
	@Override
	public boolean checkCategoryName(String name) {
		ContraintValidator.checkNotNull(name);
		try {
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(name);
			List<Category> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
		}
		return false;
	}
	
}
