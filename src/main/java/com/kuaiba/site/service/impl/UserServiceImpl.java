package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.dao.UserMapper;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.service.UserService;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;
import com.kuaiba.site.support.ValidUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper mapper;

	@Override
	public PageInfo<User> findByExample(UserExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
		List<User> list = this.findByExample(example);
		return new PageInfo<>(list);
	}

	@Override
	public int countByExample(UserExample example) {
		ValidUtils.checkNotNull(example);
		return mapper.countByExample(example);
	}

	@Override
	public void deleteByExample(UserExample example) {
		ValidUtils.checkNotNull(example);
		mapper.deleteByExample(example);		
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		mapper.deleteByPrimaryKey(id);		
	}

	@Override
	public void add(User record) {
		ValidUtils.checkNotNull(record);
		mapper.insert(record);		
	}

	@Override
	public List<User> findByExample(UserExample example) {
		ValidUtils.checkNotNull(example);
		return mapper.selectByExample(example);
	}

	@Override
	public User findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByExample(User record, UserExample example) {
		ValidUtils.checkNotNull(record, example);
		mapper.updateByExample(record, example);
	}

	@Override
	public void updateByPrimaryKey(User record) {
		ValidUtils.checkNotNull(record);
		ValidUtils.checkPrimaryKey(record.getId());
		mapper.updateByPrimaryKey(record);		
	}

	@Override
	public String findNameByEmail(String email) {
		ValidUtils.checkEmail(email);
		return mapper.selectNameByEmail(email);
	}

	@Override
	public boolean existsEmail(String email) {
		ValidUtils.checkEmail(email);
		return ( mapper.selectNameByEmail(email) == null) ?false: true;
	}

	@Override
	public User findByName(String name) {
		ValidUtils.checkNotNull(name);
		return mapper.selectByName(name);
	}

	@Override
	public boolean existsName(String name) {
		ValidUtils.checkNotNull(name);
		return (mapper.selectByName(name) == null)? false: true;
	}

	@Override
	public Result login(String username, String password) {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			User currentUser = this.findByName((String)subject.getPrincipal());
			subject.getSession().setAttribute("currentUser", currentUser);
			return ResultBuilder.ok();
		} catch (InvalidSessionException e) {
			return ResultBuilder.failed("登录失败.");
		} catch (AuthenticationException e) {
			return ResultBuilder.failed("用户名或密码错误.");
		}
	}

}
