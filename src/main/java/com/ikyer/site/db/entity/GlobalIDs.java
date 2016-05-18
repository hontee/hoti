package com.ikyer.site.db.entity;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ikyer.site.core.security.ApplicationProps;

/**
 * 常量类
 * 
 * 在静态方法中可以通过ids获取属性值，注意类加载顺序。
 * 
 * @author larry.qi
 */
@Component
public class GlobalIDs {

  @Resource
  private ApplicationProps props;
  private static GlobalIDs ids;

  /* 字符编码 */
  public static String charset() {
    return ids.props.getCharset();
  }

  /* 当前登录用户 */
  public static String loginUser() {
    return ids.props.getLoginUser();
  }

  /* 是否为管理员用户 */
  public static String adminUser() {
    return ids.props.getAdminUser();
  }

  /* 默认设置来源 */
  public static String reffer() {
    return ids.props.getReffer();
  }

  /* Cookie有效期 */
  public static int cookieMaxage() {
    return ids.props.getCookieAge();
  }

  /* 设置自动获取URL数据的浏览器网关 */
  public static String userAgent() {
    return ids.props.getFetchUserAgent();
  }

  /* 设置自动获取URL数据的超时时间 */
  public static int timeout() {
    return ids.props.getFetchTimeout();
  }

  /* 设置前端分页行数：30 */
  public static int frontRows() {
    return ids.props.getFrontRows();
  }

  /**
   * 初始化静态方法
   */
  @PostConstruct
  public void init() {
    ids = this;
    ids.props = this.props;
  }

}
