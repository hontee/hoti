package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.dao.UserMapper;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.service.UserService;
import com.kuaiba.site.support.Pagination;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper mapper;

	@Override
	public PageInfo<User> findByExample(UserExample example, Pagination p) throws Exception {
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
		List<User> list = this.findByExample(example);
		return new PageInfo<>(list);
	}

	@Override
	public int countByExample(UserExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public void deleteByExample(UserExample example) {
		mapper.deleteByExample(example);		
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		mapper.deleteByPrimaryKey(id);		
	}

	@Override
	public void add(User record) {
		mapper.insert(record);		
	}

	@Override
	public List<User> findByExample(UserExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public User findByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByExample(User record, UserExample example) {
		mapper.updateByExample(record, example);
	}

	@Override
	public void updateByPrimaryKey(User record) {
		mapper.updateByPrimaryKey(record);		
	}

}
