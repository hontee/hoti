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

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Resource
	private CategoryMapper mapper;

	@Override
	public PageInfo<Category> findByExample(CategoryExample example, Pagination p) throws Exception {
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
		List<Category> list = this.findByExample(example);
		return new PageInfo<>(list);
	}

	@Override
	public int countByExample(CategoryExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public void deleteByExample(CategoryExample example) {
		mapper.deleteByExample(example);
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void add(Category record) {
		mapper.insert(record);		
	}

	@Override
	public List<Category> findByExample(CategoryExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public Category findByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByExample(Category record, CategoryExample example) {
		mapper.updateByExample(record, example);		
	}

	@Override
	public void updateByPrimaryKey(Category record) {
		mapper.updateByPrimaryKey(record);		
	}

}
