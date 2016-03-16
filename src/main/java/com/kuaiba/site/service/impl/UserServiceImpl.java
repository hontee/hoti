package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.GlobalIDs;
import com.kuaiba.site.core.exception.AuthzException;
import com.kuaiba.site.core.exception.CreateException;
import com.kuaiba.site.core.exception.DeleteException;
import com.kuaiba.site.core.exception.ReadException;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.UpdateException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.db.dao.UserMapper;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.front.vo.UserVO;
import com.kuaiba.site.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper mapper;

	@Override
	public PageInfo<User> findByExample(UserExample example, Pagination p) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example, p);
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<User> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			throw new ReadException("分页读取数据失败", e);
		}
	}

	@Override
	public int countByExample(UserExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.countByExample(example);
		} catch (Exception e) {
			throw new ReadException("统计数据失败", e);
		}
	}

	@Override
	public void deleteByExample(UserExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			mapper.deleteByExample(example);
		} catch (Exception e) {
			throw new DeleteException("删除数据失败", e);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DeleteException("删除数据失败", e);
		}
	}

	@Override
	public void add(UserVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			User record = new User();
			record.setSaltRandom();
			record.setPasswordEncrypt(vo.getPassword(), record.getSalt()); // 密码加密
			record.setUserType(vo.getUserType());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getName());
			mapper.insert(record);
		} catch (Exception e) {
			throw new CreateException("添加数据失败", e);
		}
	}

	@Override
	public List<User> findByExample(UserExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(example);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			throw new ReadException("读取数据失败", e);
		}
	}

	@Override
	public User findByPrimaryKey(Long id) throws SecurityException { 
		try {
			ContraintValidator.checkPrimaryKey(id);
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ReadException("读取数据失败", e);
		}
	}

	@Override
	public void updateByExample(User record, UserExample example) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(record, example);
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			throw new UpdateException("更新数据失败", e);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, UserVO vo) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(vo);
			ContraintValidator.checkPrimaryKey(id);
			User record = new User();
			record.setId(id);
			record.setUserType(vo.getUserType());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setDescription(vo.getDescription());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			throw new UpdateException("更新数据失败", e);
		}
	}

	@Override
	public User findByName(String name) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(name);
			return mapper.selectByName(name);
		} catch (Exception e) {
			throw new ReadException("根据用户名查询失败", e);
		}
	}

	@Override
	public boolean existsName(String name) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(name);
			return mapper.selectByName(name) != null;
		} catch (Exception e) {
			throw new UpdateException("检测用户名失败", e);
		}
	}

	@Override
	public void login(String username, String password) throws SecurityException {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			User currentUser = this.findByName((String) subject.getPrincipal());
			Session session = subject.getSession();
			session.setAttribute(GlobalIDs.LOGIN_USER, currentUser);
			session.setAttribute(GlobalIDs.ADMIN_USER, CurrentUser.isAdmin());
		} catch (AuthenticationException e) {
			throw new AuthzException("用户权限认证失败", e);
		} catch (Exception e) {
			throw new AccountException("用户名或密码错误", e);
		}
	}
	
	@Override
	public boolean checkUserName(String name) throws SecurityException { 
		try {
			ContraintValidator.checkNotNull(name);
			UserExample example = new UserExample();
			example.createCriteria().andNameEqualTo(name);
			List<User> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
			throw new UpdateException("检测用户名失败", e);
		}
	}

}
