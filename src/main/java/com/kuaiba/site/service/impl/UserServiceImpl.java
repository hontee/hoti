package com.kuaiba.site.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.kuaiba.site.core.GlobalIDs;
import com.kuaiba.site.core.exceptions.ExceptionIds;
import com.kuaiba.site.core.exceptions.LogicException;
import com.kuaiba.site.core.security.CurrentUser;
import com.kuaiba.site.core.security.EncryptUtils;
import com.kuaiba.site.db.dao.UserMapper;
import com.kuaiba.site.db.entity.ContraintValidator;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.front.vo.UserVO;
import com.kuaiba.site.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private UserMapper mapper;

	@Override
	public PageInfo<User> findByExample(UserExample example, Pagination p) {
		ContraintValidator.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<User> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public int countByExample(UserExample example) {
		ContraintValidator.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void deleteByExample(UserExample example) {
		ContraintValidator.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ContraintValidator.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_DELETE);
		}
	}

	@Override
	public void add(UserVO vo) {
		ContraintValidator.checkNotNull(vo);
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
			throw new LogicException(ExceptionIds.LOGIC_ADD);
		}
	}

	@Override
	public List<User> findByExample(UserExample example) {
		ContraintValidator.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public User findByPrimaryKey(Long id) {
		ContraintValidator.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void updateByExample(User record, UserExample example) {
		ContraintValidator.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, UserVO vo) {
		ContraintValidator.checkNotNull(vo);
		ContraintValidator.checkPrimaryKey(id);
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
			throw new LogicException(ExceptionIds.LOGIC_UPDATE);
		}
	}

	@Override
	public User findByName(String name) {
		ContraintValidator.checkNotNull(name);
		try {
			return mapper.selectByName(name);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public boolean existsName(String name) {
		ContraintValidator.checkNotNull(name);
		try {
			return (mapper.selectByName(name) == null) ? false : true;
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.LOGIC_QUERY);
		}
	}

	@Override
	public void login(String username, String password) throws Exception {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			User currentUser = this.findByName((String) subject.getPrincipal());
			Session session = subject.getSession();
			session.setAttribute(GlobalIDs.LOGIN_USER, currentUser);
			session.setAttribute(GlobalIDs.ADMIN_USER, CurrentUser.isAdmin());
		} catch (InvalidSessionException e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.ACCOUNT_UNKNOWN);
		} catch (AuthenticationException e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.ACCOUNT_UNAUTHORIZATION);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new LogicException(ExceptionIds.ACCOUNT_DEFAULT);
		}
	}
	
	@Override
	public boolean checkUserName(String name) {
		ContraintValidator.checkNotNull(name);
		try {
			UserExample example = new UserExample();
			example.createCriteria().andNameEqualTo(name);
			List<User> list = mapper.selectByExample(example);
			ContraintValidator.checkNotNull(list);
			return !list.isEmpty();
		} catch (Exception e) {
		}
		return false;
	}

}
