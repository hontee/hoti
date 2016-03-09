package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.core.GlobalIds;
import com.kuaiba.site.core.exceptions.LogicException;
import com.kuaiba.site.core.security.EncryptUtils;
import com.kuaiba.site.db.dao.UserMapper;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.front.vo.UserVO;
import com.kuaiba.site.service.UserService;
import com.kuaiba.site.service.utils.Pagination;
import com.kuaiba.site.service.utils.ValidUtils;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private UserMapper mapper;

	@Override
	public PageInfo<User> findByExample(UserExample example, Pagination p) {
		ValidUtils.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<User> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public int countByExample(UserExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public void deleteByExample(UserExample example) {
		ValidUtils.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public void add(UserVO vo) {
		ValidUtils.checkNotNull(vo);
		try {
			User record = new User();
			record.setSalt(EncryptUtils.getRandomSalt()); // 随机盐值
			record.setPassword(EncryptUtils.encrypt(vo.getPassword(), record.getSalt())); // 密码加密
			record.setUserType(vo.getUserType());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getName());
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public List<User> findByExample(UserExample example) {
		ValidUtils.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public User findByPrimaryKey(Long id) {
		ValidUtils.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public void updateByExample(User record, UserExample example) {
		ValidUtils.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, UserVO vo) {
		ValidUtils.checkNotNull(vo);
		ValidUtils.checkPrimaryKey(id);
		try {
			User record = new User();
			record.setId(id);
			record.setUserType(vo.getUserType());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setDescription(vo.getDescription());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public User findByName(String name) {
		ValidUtils.checkNotNull(name);
		try {
			return mapper.selectByName(name);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public boolean existsName(String name) {
		ValidUtils.checkNotNull(name);
		try {
			return (mapper.selectByName(name) == null) ? false : true;
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}

	@Override
	public void login(String username, String password) throws Exception {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			User currentUser = this.findByName((String) subject.getPrincipal());
			subject.getSession().setAttribute(GlobalIds.LOGIN_USER, currentUser);
		} catch (InvalidSessionException e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		} catch (AuthenticationException e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException();
		}
	}
	
	@Override
	public boolean checkUserName(String name) {
		try {
			UserExample example = new UserExample();
			example.createCriteria().andNameEqualTo(name);
			List<User> list = mapper.selectByExample(example);
			ValidUtils.checkNotNull(list);
			return (list.isEmpty())? false: true;
		} catch (Exception e) {
		}
		return false;
	}

}
