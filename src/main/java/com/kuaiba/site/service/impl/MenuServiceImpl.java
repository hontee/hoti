package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.dao.MenuMapper;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.service.MenuService;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.ValidUtils;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Resource
	private MenuMapper mapper;

	@Override
	public PageInfo<Menu> findByExample(MenuExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
		List<Menu> list = this.findByExample(example);
		return new PageInfo<>(list);
	}

	@Override
	public int countByExample(MenuExample example) {
		ValidUtils.checkNotNull(example);
		return mapper.countByExample(example);
	}

	@Override
	public void deleteByExample(MenuExample example) {
		ValidUtils.checkNotNull(example);
		mapper.deleteByExample(example);
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void add(Menu record) {
		ValidUtils.checkNotNull(record);
		mapper.insert(record);
	}

	@Override
	public List<Menu> findByExample(MenuExample example) {
		ValidUtils.checkNotNull(example);
		return mapper.selectByExample(example);
	}

	@Override
	public Menu findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByExample(Menu record, MenuExample example) {
		ValidUtils.checkNotNull(record, example);
		mapper.updateByExample(record, example);
	}

	@Override
	public void updateByPrimaryKey(Menu record) {
		ValidUtils.checkNotNull(record);
		ValidUtils.checkPrimaryKey(record.getId());
		mapper.updateByPrimaryKey(record);		
	}

}
