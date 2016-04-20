package com.hoti.site.front.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.core.security.AuthzUtil;
import com.hoti.site.db.entity.SiteResponse;
import com.hoti.site.rest.BaseService;

@RestController
@Scope("prototype")
public class RestAPIController {
  
  private Logger logger = LoggerFactory.getLogger(RestAPIController.class);

  @Resource
  private BaseService service;

  /**
   * @WebAPI 关注产品
   * @param id
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/bookmarks/{id}/follow", method = RequestMethod.POST)
  public SiteResponse followBookmark(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
    logger.info("关注产品: {}", id);
    service.followProduct(AuthzUtil.getUserId(), id);
    return SiteUtil.ok();
  }

  /**
   * @WebAPI 取消关注产品
   * @param id
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/bookmarks/{id}/unfollow", method = RequestMethod.POST)
  public SiteResponse unfollowBookmark(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("取消关注产品: {}", id);    
    service.unfollowProduct(AuthzUtil.getUserId(), id);
    return SiteUtil.ok();
  }

  /**
   * @WebAPI 分享产品
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/share", method = RequestMethod.POST)
  public SiteResponse share(@RequestParam String url, HttpServletRequest request) throws SecurityException {
    logger.info("分享产品", url);
    service.addRecommend(url);
    return SiteUtil.ok();
  }

  /**
   * @WebAPI 关注主题
   * @param id
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/groups/{id}/follow", method = RequestMethod.POST)
  public SiteResponse followGroup(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("关注主题：{}", id);
    service.followTopic(AuthzUtil.getUserId(), id);
    return SiteUtil.ok();
  }

  /**
   * @WebAPI 取消关注主题
   * @param id
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/groups/{id}/unfollow", method = RequestMethod.POST)
  public SiteResponse unfollowGroup(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
    logger.info("取消关注主题: {}", id);
    service.unfollowTopic(AuthzUtil.getUserId(), id);
    return SiteUtil.ok();
  }

  /**
   * @WebAPI 用户登录提交
   * @param username
   * @param password
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public SiteResponse login(@RequestParam String username, @RequestParam String password,
      HttpServletRequest request) throws SecurityException {
    logger.info("用户登录：{}", username);
    service.authenticate(username, password);
    return SiteUtil.ok();
  }

}
