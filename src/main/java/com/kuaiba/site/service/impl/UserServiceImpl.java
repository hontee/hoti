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
import com.kuaiba.site.Constants;
import com.kuaiba.site.db.dao.UserMapper;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.security.EncryptUtils;
import com.kuaiba.site.service.UserService;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;
import com.kuaiba.site.support.ValidUtils;
import com.kuaiba.site.vo.UserVO;

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
	public void add(UserVO vo) {
		ValidUtils.checkNotNull(vo);
		
		User record = new User();
		record.setEmail(vo.getEmail());
		record.setIsEmailSet((byte)0);
		record.setSalt(EncryptUtils.getRandomSalt()); // 随机盐值
		record.setPassword(EncryptUtils.encrypt(vo.getPassword(), record.getSalt())); // 密码加密
		record.setUserType(vo.getUserType());
		record.setName(vo.getName());
		record.setState(vo.getState());
		record.setTitle(vo.getName());
		
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
	public void updateByPrimaryKey(Long id, UserVO vo) {
		ValidUtils.checkNotNull(vo);
		ValidUtils.checkPrimaryKey(id);
		
		User record = new User();
		record.setId(id);
		record.setEmail(vo.getEmail());
		record.setUserType(vo.getUserType());
		record.setName(vo.getName());
		record.setState(vo.getState());
		record.setTitle(vo.getTitle());
		record.setDescription(vo.getDescription());
		
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
			ValidUtils.checkEmail(username);
			username = this.findNameByEmail(username);
		} catch (Exception e) {
			// 非邮箱登录
		}
		
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			User currentUser = this.findByName((String)subject.getPrincipal());
			subject.getSession().setAttribute(Constants.LOGIN_USER, currentUser);
			return ResultBuilder.ok();
		} catch (InvalidSessionException e) {
			return ResultBuilder.failed("登录失败.");
		} catch (AuthenticationException e) {
			return ResultBuilder.failed("用户名或密码错误.");
		}
	}

}
