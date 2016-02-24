package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.dao.CategoryMapper;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.CategoryExample;
import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.ValidUtils;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Resource
	private CategoryMapper mapper;

	@Override
	public PageInfo<Category> findByExample(CategoryExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
		List<Category> list = this.findByExample(example);
		return new PageInfo<>(list);
	}

	@Override
	public int countByExample(CategoryExample example) {
		ValidUtils.checkNotNull(example);
		return mapper.countByExample(example);
	}

	@Override
	public void deleteByExample(CategoryExample example) {
		ValidUtils.checkNotNull(example);
		mapper.deleteByExample(example);
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void add(Category record) {
		ValidUtils.checkNotNull(record);
		mapper.insert(record);		
	}

	@Override
	public List<Category> findByExample(CategoryExample example) {
		ValidUtils.checkNotNull(example);
		return mapper.selectByExample(example);
	}

	@Override
	public Category findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByExample(Category record, CategoryExample example) {
		ValidUtils.checkNotNull(record, example);
		mapper.updateByExample(record, example);		
	}

	@Override
	public void updateByPrimaryKey(Category record) {
		ValidUtils.checkNotNull(record);
		ValidUtils.checkPrimaryKey(record.getId());
		mapper.updateByPrimaryKey(record);
	}

}
