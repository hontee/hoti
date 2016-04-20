package com.hoti.site.core.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.hoti.site.db.entity.GlobalIDs;
import com.hoti.site.db.entity.User;

/**
 * 登录用户信息
 * 
 * @author larry.qi
 */
public class AuthzUtil {

  static Logger logger = LoggerFactory.getLogger(AuthzUtil.class);

  static Long UN_LOGIN_USER_ID = -1L;

  /**
   * 获取当前登录用户, 未登录则返回 NULL
   * 
   * @return
   */
  public static User getUser() {
    try {
      Subject subject = SecurityUtils.getSubject();
      Session session = subject.getSession();
      Object currentUser = session.getAttribute(GlobalIDs.CURRENT_USER);

      if (currentUser instanceof User) {
        return (User) currentUser;
      }

    } catch (Exception e) {
      logger.debug(Throwables.getStackTraceAsString(e));
    }

    return null;
  }

  /**
   * 获取当前登录用户名, 如果当前用户未登录则返回 "anonymous".
   * 
   * @return
   */
  public static String getUsername() {
    return isAuthorized() ? getUser().getName() : "anonymous";
  }

  /**
   * 取得当前用户的登录id, 如果当前用户未登录则返回 UN_LOGIN_USER_ID.
   */
  public static Long getUserId() {
    return isAuthorized() ? getUser().getId() : UN_LOGIN_USER_ID;
  }

  /**
   * 当前用户是否登录, 未登录返回false
   * 
   * @return
   */
  public static boolean isAuthorized() {
    return getUser() != null;
  }

  /**
   * 用户是否为管理员, 未登录则返回false
   * 
   * @return
   */
  public static boolean isAdmin() {
    return isAuthorized() ? (getUser().getType() == 2L) : false;
  }

}
