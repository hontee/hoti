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
import com.kuaiba.site.Constants;
import com.kuaiba.site.db.dao.UserMapper;
import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.exceptions.BusinessException;
import com.kuaiba.site.front.result.Result;
import com.kuaiba.site.front.result.ResultBuilder;
import com.kuaiba.site.front.vo.UserVO;
import com.kuaiba.site.security.EncryptUtils;
import com.kuaiba.site.service.UserService;
import com.kuaiba.site.service.kit.Pagination;
import com.kuaiba.site.service.kit.ValidKit;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private UserMapper mapper;

	@Override
	public PageInfo<User> findByExample(UserExample example, Pagination p) {
		ValidKit.checkNotNull(example, p);
		try {
			PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
			List<User> list = this.findByExample(example);
			return new PageInfo<>(list);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public int countByExample(UserExample example) {
		ValidKit.checkNotNull(example);
		try {
			return mapper.countByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public void deleteByExample(UserExample example) {
		ValidKit.checkNotNull(example);
		try {
			mapper.deleteByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		ValidKit.checkPrimaryKey(id);
		try {
			mapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public void add(UserVO vo) {
		ValidKit.checkNotNull(vo);
		try {
			User record = new User();
			record.setEmail(vo.getEmail());
			record.setIsEmailSet((byte) 0);
			record.setSalt(EncryptUtils.getRandomSalt()); // 随机盐值
			record.setPassword(EncryptUtils.encrypt(vo.getPassword(), record.getSalt())); // 密码加密
			record.setUserType(vo.getUserType());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getName());
			mapper.insert(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public List<User> findByExample(UserExample example) {
		ValidKit.checkNotNull(example);
		try {
			return mapper.selectByExample(example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public User findByPrimaryKey(Long id) {
		ValidKit.checkPrimaryKey(id);
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateByExample(User record, UserExample example) {
		ValidKit.checkNotNull(record, example);
		try {
			mapper.updateByExample(record, example);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public void updateByPrimaryKey(Long id, UserVO vo) {
		ValidKit.checkNotNull(vo);
		ValidKit.checkPrimaryKey(id);
		try {
			User record = new User();
			record.setId(id);
			record.setEmail(vo.getEmail());
			record.setUserType(vo.getUserType());
			record.setName(vo.getName());
			record.setState(vo.getState());
			record.setTitle(vo.getTitle());
			record.setDescription(vo.getDescription());
			mapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public String findNameByEmail(String email) {
		ValidKit.checkEmail(email);
		try {
			return mapper.selectNameByEmail(email);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public boolean existsEmail(String email) {
		ValidKit.checkEmail(email);
		try {
			return (mapper.selectNameByEmail(email) == null) ? false : true;
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public User findByName(String name) {
		ValidKit.checkNotNull(name);
		try {
			return mapper.selectByName(name);
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public boolean existsName(String name) {
		ValidKit.checkNotNull(name);
		try {
			return (mapper.selectByName(name) == null) ? false : true;
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

	@Override
	public Result login(String username, String password) {
		try {
			if (ValidKit.checkEmail(username)){
				username = this.findNameByEmail(username);
			}
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			User currentUser = this.findByName((String) subject.getPrincipal());
			subject.getSession().setAttribute(Constants.LOGIN_USER, currentUser);
			return ResultBuilder.ok();
		} catch (InvalidSessionException e) {
			return ResultBuilder.failed("登录失败.");
		} catch (AuthenticationException e) {
			return ResultBuilder.failed("用户名或密码错误.");
		} catch (Exception e) {
			logger.debug(Throwables.getStackTraceAsString(e));
			throw new BusinessException(e);
		}
	}

}
