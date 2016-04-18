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
import com.hoti.site.db.entity.SiteResponse;
import com.hoti.site.db.entity.TableIDs;
import com.hoti.site.service.ActivityService;
import com.hoti.site.service.BookmarkService;
import com.hoti.site.service.GroupService;
import com.hoti.site.service.RecommendService;
import com.hoti.site.service.UserService;

@RestController
@Scope("prototype")
public class RestAPIController {
  
  private Logger logger = LoggerFactory.getLogger(RestAPIController.class);

  @Resource
  private BookmarkService bs;
  @Resource
  private RecommendService rs;
  @Resource
  private GroupService gs;
  @Resource
  private UserService us;
  @Resource
  private ActivityService as;

  /**
   * @WebAPI 关注站点
   * @param id
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/bookmarks/{id}/follow", method = RequestMethod.POST)
  public SiteResponse followBookmark(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
    logger.info("关注站点: {}", id);
    as.addLogger("关注站点", TableIDs.BOOKMARK_FOLLOW, id.toString(), request);
    
    bs.follow(id);
    return SiteUtil.ok();
  }

  /**
   * @WebAPI 取消关注站点
   * @param id
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/bookmarks/{id}/unfollow", method = RequestMethod.POST)
  public SiteResponse unfollowBookmark(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("取消关注站点: {}", id);    
    as.addLogger("取消关注站点", TableIDs.BOOKMARK_FOLLOW, id.toString(), request);

    bs.unfollow(id);
    return SiteUtil.ok();
  }

  /**
   * @WebAPI 分享站点
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/share", method = RequestMethod.POST)
  public SiteResponse share(@RequestParam String url, HttpServletRequest request) throws SecurityException {
    logger.info("分享站点", url);
    as.addLogger("分享站点", TableIDs.RECOMMEND, url, request);
    
    rs.add(url);
    return SiteUtil.ok();
  }

  /**
   * @WebAPI 关注群组
   * @param id
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/groups/{id}/follow", method = RequestMethod.POST)
  public SiteResponse followGroup(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("关注主题：{}", id);
    as.addLogger("关注主题", TableIDs.GROUP_FOLLOW, id.toString(), request);
  
    gs.follow(id);
    return SiteUtil.ok();
  }

  /**
   * @WebAPI 取消关注群组
   * @param id
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/groups/{id}/unfollow", method = RequestMethod.POST)
  public SiteResponse unfollowGroup(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
    logger.info("取消关注主题: {}", id);
    as.addLogger("取消关注主题", TableIDs.GROUP_FOLLOW, id.toString(), request);
    
    gs.unfollow(id);
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
    as.addLogger("用户登录", TableIDs.USER, username, request);
    
    us.authenticate(username, password);
    return SiteUtil.ok();
  }

}
