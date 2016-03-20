package com.kuaiba.site.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.NotFoundException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UpdateException;
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
	
	@Resource
	private BookmarkFollowMapper bfMapper;
	
	@Resource
	private CategoryMapper mapper;

	@Override
	public PageInfo<Category> findByExample(CategoryExample example, Pagination p) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Category> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取分类失败", e);
		}
	}

	@Override
	public int countByExample(CategoryExample example) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计分类失败", e);
		}
	}

	@Override
	public void deleteByExample(CategoryExample example) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除分类失败", e);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) throws SecurityException {
		try {
			ContraintValidator.checkPrimaryKey(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除分类失败", e);
		}
	}

	@Override
	public void add(CategoryVO vo) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(vo);
			Category record = new Category();
			record.setName(vo.getName());
			record.setTitle(vo.getTitle());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setDomain(vo.getDomain());
			record.setCreateBy(CurrentUser.getCurrentUserId());
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加分类失败", e);
		}
	}

	@Override
	public List<Category> findByExample(CategoryExample example) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取分类失败", e);
		}
	}

	@Override
	public Category findByPrimaryKey(Long id) throws SecurityException {
		try {
			ContraintValidator.checkPrimaryKey(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取分类失败", e);
		}
	}

	@Override
	public void updateByExample(Category record, CategoryExample example) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新分类失败", e);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, CategoryVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			ContraintValidator.checkPrimaryKey(id);
			Category record = new Category();
			record.setId(id);
			record.setName(vo.getName());
			record.setTitle(vo.getTitle());
			record.setDescription(vo.getDescription());
			record.setState(vo.getState());
			record.setDomain(vo.getDomain());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新分类失败", e);
		}
	}
	
	@Override
	public void updateByPrimaryKey(Long id, long count) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			Category record = new Category();
			record.setId(id);
			record.setCount(count);
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新统计分类失败", e);
		}
	}

	@Override
	public List<Category> findByOrganization(Long domain) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(domain);
			return mapper.selectByDomain(domain);
		} catch (Exception e) {
			throw new ReadException("读取分类失败", e);
		}
	}

	@Override
	public List<Category> findByCollect(CategoryExample example) throws SecurityException {
		try {
			ContraintValidator.checkNotNull(example);
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
			throw new ReadException("读取分类失败", e);
		}
	}
	
	@Override
	public boolean checkCategoryName(String name) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(name);
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(name);
			List<Category> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
			throw new NotFoundException("检测分类名称失败", e);
		}
	}
	
}
