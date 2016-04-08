package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.AuthzException;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.PasswordException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.core.exception.ValidationException;
import com.kuaiba.site.core.security.AuthzUtil;
import com.kuaiba.site.db.dao.UserMapper;
import com.kuaiba.site.db.entity.GlobalIDs;
import com.kuaiba.site.db.entity.PagerUtil;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.db.entity.VUtil;
import com.kuaiba.site.front.vo.UserVO;
import com.kuaiba.site.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper mapper;

	@Override
	public PageInfo<User> find(UserExample example, Pagination p) throws SecurityException { 
		try {
			VUtil.assertNotNull(example, p);
			PagerUtil.startPage(p);
			List<User> list = findAll(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取用户失败", e);
		}
	}

	@Override
	public int count(UserExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计用户失败", e);
		}
	}

	@Override
	public void delete(UserExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除用户失败", e);
		}
	}

	@Override
	public void delete(Long id) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除用户失败", e);
		}
	}

	@Override
	public void add(UserVO vo) throws SecurityException { 
		try {
			VUtil.assertNotNull(vo);
			User record = new User();
			record.setSaltRandom();
			record.setPasswordEncrypt(vo.getPassword(), record.getSalt()); // 密码加密
			record.setUserType(vo.getUserType());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getName());
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加用户失败", e);
		}
	}

	@Override
	public List<User> findAll(UserExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取用户失败", e);
		}
	}

	@Override
	public User findOne(Long id) throws SecurityException { 
		try {
			VUtil.assertNotNull(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取用户失败", e);
		}
	}

	@Override
	public void update(User record, UserExample example) throws SecurityException { 
		try {
			VUtil.assertNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新用户失败", e);
		}
	}

	@Override
	public void update(Long id, UserVO vo) throws SecurityException { 
		try {
			VUtil.assertNotNull(vo, id);
			User record = new User();
			record.setId(id);
			record.setUserType(vo.getUserType());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setDescription(vo.getDescription());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新用户失败", e);
		}
	}
	
	@Override
	public void update(Long id, String password) throws SecurityException {
		try {
			VUtil.assertNotNull(password, id);
			User record = findOne(id);
			record.setId(id);
			record.setPasswordEncrypt(password, record.getSalt());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new PasswordException("更新用户密码失败", e);
		}
	}

	@Override
	public User findByName(String name) throws SecurityException { 
		try {
			VUtil.assertNotNull(name);
			UserExample example = new UserExample();
			example.createCriteria().andNameEqualTo(name);
			List<User> list = findAll(example);
			return CollectionUtils.isNotEmpty(list)? list.get(0): null;
		} catch (Exception e) {
			throw new ReadException("查询用户名失败", e);
		}
	}

	@Override
	public boolean validate(String name) throws SecurityException { 
		try {
			return findByName(name) != null;
		} catch (Exception e) {
			throw new ValidationException("验证用户名" + name + "失败", e);
		}
	}

	@Override
	public void authenticate(String username, String password) throws SecurityException {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			String principal = (String) subject.getPrincipal();
			User currentUser = findByName(principal);
			Session session = subject.getSession();
			session.setAttribute(GlobalIDs.CURRENT_USER, currentUser);
			session.setAttribute(GlobalIDs.ADMIN_USER, AuthzUtil.isAdmin());
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw new AuthzException("用户授权失败", e);
		} catch (Exception e) {
			throw new AccountException("用户名或密码错误", e);
		}
	}

}
