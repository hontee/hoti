package com.ikyer.site.front.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ikyer.site.core.exception.SecurityException;
import com.ikyer.site.db.entity.FetchFactory;
import com.ikyer.site.db.entity.Recommend;
import com.ikyer.site.db.entity.Topic;
import com.ikyer.site.db.entity.User;
import com.ikyer.site.front.vo.RecommendVO;
import com.ikyer.site.front.vo.ResponseVO;
import com.ikyer.site.front.vo.TopicVO;
import com.ikyer.site.rest.BaseService;

@RestController
@Scope("prototype")
public class APIController extends BaseController {
  
  private Logger logger = LoggerFactory.getLogger(APIController.class);

  @Resource
  private BaseService service;

  /**
   * 用户登录提交
   * 
   * @param username
   * @param password
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseVO login(@RequestParam String username, @RequestParam String password,
      @RequestParam(defaultValue = "/") String redirect, HttpServletRequest request)
          throws SecurityException {
    logger.info("用户登录：{}", username);
    service.authenticate(username, password);
    return buildResponse(redirect);
  }
  
  /**
   * 用户登录提交
   * 
   * @param username
   * @param password
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/{name}/settings", method = RequestMethod.POST)
  public ResponseVO userSettings(@PathVariable String name, @RequestParam String title,
      String description, HttpServletRequest request) throws SecurityException {
    logger.info("用户保存设置：{}", name);
    User user = service.findUser(name);
    user.setTitle(title);
    user.setDescription(description);
    service.updateUser(user);
    return buildResponse();
  }

  /**
   * 分享产品
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/recommend", method = RequestMethod.POST)
  public ResponseVO recommend(RecommendVO vo, HttpServletRequest request) throws SecurityException {
    logger.info("推荐产品: {}", JSON.toJSONString(vo));

    Recommend record = FetchFactory.get(vo.getUrl());

    if (vo.getTid() != null) {
      Topic t = service.findTopic(vo.getTid());
      record.setTid(t.getId());
      record.setTopic(t.getTitle());
    }
    
    if (StringUtils.isNotEmpty(vo.getTitle())) {
      record.setTitle(vo.getTitle());
    }
    
    if (StringUtils.isNotEmpty(vo.getKeywords())) {
      record.setKeywords(vo.getKeywords());
    }
    
    if (StringUtils.isNotBlank(vo.getDescription())) {
      record.setDescription(vo.getDescription());
    }
    
    service.addRecommend(record);
    return buildResponse();
  }
  
  /**
   * 创建主题
   * 
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/topics/new", method = RequestMethod.POST)
  public ResponseVO createTopic(Model model, TopicVO vo, HttpServletRequest request) throws SecurityException {
    logger.info("创建主题：{}", JSON.toJSONString(vo));
    // TODO
    /*vo.setState((byte)2);*/ // 待审核
    service.addTopic(vo);
    return buildResponse();
  }

  /**
   * 关注产品
   * 
   * @param id 产品ID
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/products/{id}/follow", method = RequestMethod.POST)
  public ResponseVO followProduct(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("关注产品: {}", id);
    service.followProduct(id);
    return buildResponse();
  }

  /**
   * 取消关注产品
   * 
   * @param id
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/products/{id}/unfollow", method = RequestMethod.POST)
  public ResponseVO unfollowProduct(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("取消关注产品: {}", id);
    service.unfollowProduct(id);
    return buildResponse();
  }

  /**
   * 关注主题
   * 
   * @param id
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/topics/{id}/follow", method = RequestMethod.POST)
  public ResponseVO followTopic(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("关注主题：{}", id);
    service.followTopic(id);
    return buildResponse();
  }

  /**
   * 取消关注主题
   * 
   * @param id
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = {"user", "admin"})
  @RequestMapping(value = "/topics/{id}/unfollow", method = RequestMethod.POST)
  public ResponseVO unfollowTopic(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("取消关注主题: {}", id);
    service.unfollowTopic(id);
    return buildResponse();
  }

}
