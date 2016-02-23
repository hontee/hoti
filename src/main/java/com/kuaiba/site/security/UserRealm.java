package com.kuaiba.site.security;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaiba.site.db.entity.User;
import com.kuaiba.site.db.entity.UserExample;
import com.kuaiba.site.service.UserService;

/**
 * 自定义用户Realm
 * @author larry.qi
 */
public class UserRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(UserRealm.class);
	
	private UserService service;
	
	public void setService(UserService service) {
		this.service = service;
	}

	/**
	 * 登录成功的用户：角色和权限设置<br>
	 * TODO: 由于SHIRO过滤器每次都会进行权限验证，Roles和Permissions最好使用缓存
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("为登录成功的用户：{}，添加角色和权限", principals.getPrimaryPrincipal());
		return new SimpleAuthorizationInfo();
	}

	/**
	 * 用户身份验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		logger.info("用户身份验证：{}", username);
		
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(username);
		List<User> list = service.findByExample(example);
		
		if (list == null || list.isEmpty()) {
			throw new UnknownAccountException("用户名或密码错误");
		}
		
		User currentUser = list.get(0); // 登录用户
		
		// 判断用户状态
		if (currentUser.getState() != 1) {
			throw new LockedAccountException("帐号被锁定");
		}
		
		// 授权登录
		return new SimpleAuthenticationInfo(currentUser.getName(), currentUser.getPassword(),
				ByteSource.Util.bytes(currentUser.getSalt()), getName());
	}
	
}
