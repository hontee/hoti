package com.kuaiba.site.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.cache.CacheIDs;
import com.kuaiba.site.core.cache.Memcacheds;
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
import com.kuaiba.site.db.entity.VUtil;
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
	public PageInfo<Category> search(CategoryExample example, Pagination p) throws SecurityException {
		try {
			VUtil.assertNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<Category> list = this.read(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取分类失败", e);
		}
	}

	@Override
	public int count(CategoryExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计分类失败", e);
		}
	}

	@Override
	public void delete(CategoryExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除分类失败", e);
		}
	}

	@Override
	public void delete(Long id) throws SecurityException {
		try {
			VUtil.assertNotNull(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除分类失败", e);
		}
	}

	@Override
	public void add(CategoryVO vo) throws SecurityException {
		try {
			VUtil.assertNotNull(vo);
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
	public List<Category> read(CategoryExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取分类失败", e);
		}
	}

	@Override
	public Category read(Long id) throws SecurityException {
		
		VUtil.assertNotNull(id);
		List<Category> list = this.getCategories();
		
		for (Category category : list) {
			if (id.equals(category.getId())) {
				return category;
			}
		}
		
		return new Category();
	}

	@Override
	public void update(Category record, CategoryExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新分类失败", e);
		}
	}

	@Override
	public void update(Long id, CategoryVO vo) throws SecurityException { 
		try {
			VUtil.assertNotNull(vo, id);
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
	public void update(Long id, long count) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			Category record = new Category();
			record.setId(id);
			record.setCount(count);
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新统计分类失败", e);
		}
	}

	@Override
	public List<Category> search(Long domain) throws SecurityException { 
		try {
			VUtil.assertNotNull(domain);
			return mapper.selectByDomain(domain);
		} catch (Exception e) {
			throw new ReadException("读取分类失败", e);
		}
	}

	@Override
	public List<Category> search(CategoryExample example) throws SecurityException {
		try {
			VUtil.assertNotNull(example);
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
	public boolean validate(String name) throws SecurityException { 
		try {
			VUtil.assertNotNull(name);
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(name);
			List<Category> list = mapper.selectByExample(example);
			VUtil.assertNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
			throw new NotFoundException("检测分类名称失败", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() throws SecurityException {
		List<Category> list = new ArrayList<>();
		
		if (Memcacheds.exists(CacheIDs.CATES)) {
			list = (List<Category>) Memcacheds.get(CacheIDs.CATES);
		} else {
			// 从数据库中获取
			list = read(new CategoryExample());
			Memcacheds.set(CacheIDs.CATES, 1000 * 60 * 30, list);
		}
		
		return list;
	}
	
}
