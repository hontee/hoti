package com.hoti.site.front.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.core.security.ApplicationProps;
import com.hoti.site.core.security.AuthzUtil;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.Product;
import com.hoti.site.db.entity.ProductExample;
import com.hoti.site.db.entity.Topic;
import com.hoti.site.db.entity.TopicExample;
import com.hoti.site.db.entity.TopicProduct;
import com.hoti.site.db.entity.User;
import com.hoti.site.rest.BaseService;

@Controller
@Scope("prototype")
public class FrontController extends BaseController {

  private Logger logger = LoggerFactory.getLogger(FrontController.class);
  
  @Resource
  private BaseService service;
  @Resource
  private ApplicationProps props;

  /**
   * 用户登录页
   * 
   * @return
   * @throws SecurityException 
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(HttpServletRequest request, Model model) throws SecurityException {
    
    if (AuthzUtil.isAuthorized()) {
      return redirect("/");
    } 
    
    addHeader(model, props.getSeoLogin(), request);
    return "login.ftl";
  }

  /**
   * 退出登录
   * 
   * @return
   */
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(HttpServletRequest request, Model model) {
    SecurityUtils.getSubject().logout();
    return redirect("/");
  }
  
  /**
   * 用户主页
   * 
   * @param name
   * @param p
   * @param model
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/{name}/dashbord", method = RequestMethod.GET)
  public String userProducts(@PathVariable String name, Pagination p, Model model,
      HttpServletRequest request) throws SecurityException {
    initPagination(p);
    User user = service.findUser(name);
    PageInfo<Product> pageInfo = service.findUserProducts(user.getId(), p);
    
    addUser(model, user);
    addHeader(model, name, request);
    addProducts(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "product");
    return "user.ftl";
  }
  
  /**
   * 用户主题
   * 
   * @param name
   * @param p
   * @param model
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/{name}/topics", method = RequestMethod.GET)
  public String userTopics(@PathVariable String name, Pagination p, Model model,
      HttpServletRequest request) throws SecurityException {
    initPagination(p);
    User user = service.findUser(name);
    PageInfo<Topic> pageInfo = service.findUserTopics(user.getId(), p);
    
    addUser(model, user);
    addHeader(model, name, request);
    addTopics(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "topic");
    return "user.ftl";
  }
  
  /**
   * 用户设置
   * 
   * @param name
   * @param p
   * @param model
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/{name}/settings", method = RequestMethod.GET)
  public String userSettings(@PathVariable String name, Pagination p, Model model,
      HttpServletRequest request) throws SecurityException {
    User user = service.findUser(name);
    addUser(model, user);
    addHeader(model, name, request);
    return "settings.ftl";
  }

  /**
   * 首页 [我的产品]，如果用户未登录则重定向到 [精选]
   * 
   * @param p 分页组件
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String homeByUser(Pagination p, Model model, HttpServletRequest request)
      throws SecurityException {
    
    /* 用户未登录，则重定向到 [精选] */
    if (!AuthzUtil.isAuthorized()) {
      return redirect("/pick");
    }

    initPagination(p);
    PageInfo<Product> pageInfo = service.findUserProducts(AuthzUtil.getUserId(), p);

    addHeader(model, AuthzUtil.getUsername(), request);
    addProducts(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "my");
    return "home.ftl";
  }

  /**
   * 首页 [精选]
   * 
   * @param p 分页组件
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/pick", method = RequestMethod.GET)
  public String homeByPick(Pagination p, Model model, HttpServletRequest request)
      throws SecurityException {

    initPagination(p);
    ProductExample example = new ProductExample();
    ProductExample.Criteria criteria = example.createCriteria();
    criteria.andPickEqualTo((byte) 1); // 精选
    criteria.andStateEqualTo((byte) 1); // 状态为启动
    PageInfo<Product> pageInfo = service.findProducts(example, p);

    addHeader(model, props.getSeoSlogan(), request);
    addProducts(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "pick");
    return "home.ftl";
  }

  /**
   * 首页 [最新]
   * 
   * @param p 分页组件
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/newest", method = RequestMethod.GET)
  public String homeByNewest(Pagination p, Model model, HttpServletRequest request)
      throws SecurityException {

    initPagination(p, "created", "desc");
    ProductExample example = new ProductExample();
    ProductExample.Criteria criteria = example.createCriteria();
    criteria.andStateEqualTo((byte) 1); // 状态为启动
    PageInfo<Product> pageInfo = service.findProducts(example, p);

    addHeader(model, props.getSeoSlogan(), request);
    addProducts(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "newest");
    return "home.ftl";
  }

  /**
   * 首页 [最热]
   * 
   * @param p 分页组件
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/popular", method = RequestMethod.GET)
  public String homeByPopular(Pagination p, Model model, HttpServletRequest request)
      throws SecurityException {

    initPagination(p, "star", "desc");
    ProductExample example = new ProductExample();
    ProductExample.Criteria criteria = example.createCriteria();
    criteria.andStateEqualTo((byte) 1); // 状态为启动
    PageInfo<Product> pageInfo = service.findProducts(example, p);

    addHeader(model, props.getSeoSlogan(), request);
    addProducts(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "popular");
    return "home.ftl";
  }

  /**
   * 主题 [我的主题]，如果用户未登录则重定向到 [精选主题]
   * 
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/topics", method = RequestMethod.GET)
  public String topicByUser(Pagination p, Model model, HttpServletRequest request)
      throws SecurityException {

    /* 用户未登录，则重定向到 [精选] */
    if (!AuthzUtil.isAuthorized()) {
      return redirect("/topics/pick");
    }

    initPagination(p);
    PageInfo<Topic> pageInfo = service.findUserTopics(AuthzUtil.getUserId(), p);

    addHeader(model, props.getSeoTopic(), request);
    addTopics(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "my");
    return "topic.ftl";
  }

  /**
   * 主题 [精选]
   * 
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/topics/pick", method = RequestMethod.GET)
  public String topicByPick(Pagination p, Model model, HttpServletRequest request)
      throws SecurityException {

    initPagination(p);
    TopicExample example = new TopicExample();
    TopicExample.Criteria criteria = example.createCriteria();
    criteria.andPickEqualTo((byte) 1); // 精选
    criteria.andStateEqualTo((byte) 1); // 状态为启动
    PageInfo<Topic> pageInfo = service.findTopics(example, p);

    addHeader(model, props.getSeoTopic(), request);
    addTopics(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "pick");
    return "topic.ftl";
  }

  /**
   * 主题 [最新]
   * 
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/topics/newest", method = RequestMethod.GET)
  public String topicByNewest(Pagination p, Model model, HttpServletRequest request)
      throws SecurityException {

    initPagination(p, "created", "desc");
    TopicExample example = new TopicExample();
    TopicExample.Criteria criteria = example.createCriteria();
    criteria.andStateEqualTo((byte) 1); // 状态为启动
    PageInfo<Topic> pageInfo = service.findTopics(example, p);

    addHeader(model, props.getSeoTopic(), request);
    addTopics(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "newest");
    return "topic.ftl";
  }

  /**
   * 主题 [最热]
   * 
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/topics/popular", method = RequestMethod.GET)
  public String topicByPopular(Pagination p, Model model, HttpServletRequest request)
      throws SecurityException {

    initPagination(p, "star", "desc");
    TopicExample example = new TopicExample();
    TopicExample.Criteria criteria = example.createCriteria();
    criteria.andStateEqualTo((byte) 1); // 状态为启动
    PageInfo<Topic> pageInfo = service.findTopics(example, p);

    addHeader(model, props.getSeoTopic(), request);
    addTopics(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "popular");
    return "topic.ftl";
  }

  /**
   * 查找主题下的产品 [最新]
   * 
   * @param id
   * @param model
   * @param p
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/topics/{id}", method = RequestMethod.GET)
  public String tpByNewest(@PathVariable Long id, Model model, Pagination p,
      HttpServletRequest request) throws SecurityException {

    initPagination(p, "created", "desc");
    Topic record = service.findTopic(id);
    PageInfo<Product> pageInfo = service.findTopicProducts(new TopicProduct(id), p);

    addHeader(model, record.getTitle(), request);
    addTopic(model, record);
    addProducts(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "newest");
    return "tp.ftl";
  }

  /**
   * 查找主题下的产品 [最热]
   * 
   * @param id
   * @param model
   * @param p
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/topics/{id}/popular", method = RequestMethod.GET)
  public String tpByPopular(@PathVariable Long id, Model model, Pagination p,
      HttpServletRequest request) throws SecurityException {

    initPagination(p, "star", "desc");
    Topic record = service.findTopic(id);
    PageInfo<Product> pageInfo = service.findTopicProducts(new TopicProduct(id), p);

    addHeader(model, record.getTitle(), request);
    addTopic(model, record);
    addProducts(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "popular");
    return "tp.ftl";
  }

  /**
   * 查找主题下的产品 [精选]
   * 
   * @param id
   * @param model
   * @param p
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/topics/{id}/pick", method = RequestMethod.GET)
  public String tpByPick(@PathVariable Long id, Model model, Pagination p,
      HttpServletRequest request) throws SecurityException {

    initPagination(p);
    Topic record = service.findTopic(id);
    TopicProduct tp = new TopicProduct(id);
    tp.setPick((byte) 1); // 查询精选

    PageInfo<Product> pageInfo = service.findTopicProducts(tp, p);
    addHeader(model, record.getTitle(), request);
    addTopic(model, record);
    addProducts(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "pick");
    return "tp.ftl";
  }

  /**
   * 搜索产品结果页
   * 
   * @param q
   * @param p
   * @param model
   * @return
   * @throws SecurityException
   * @注意：解决请求参数乱码问题需修改TOMCAT <Connector> 添加 URIEncoding="UTF-8"
   */
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public String searchProduct(@RequestParam String q, Pagination p, Model model,
      HttpServletRequest request) throws SecurityException {

    logger.info("用户输入搜索：{}", q);
    initPagination(p);
    ProductExample example = new ProductExample();
    ProductExample.Criteria criteria = example.createCriteria();
    criteria.andStateEqualTo((byte) 1); // 状态为启动

    if (StringUtils.isNotEmpty(q)) {
      criteria.andTitleLike("%" + q + "%"); // 模糊查询
    }

    PageInfo<Product> pageInfo = service.findProducts(example, p);
    logger.info("用户搜索产品数：{}", pageInfo.getTotal());

    addHeader(model, props.getSeoSearchProduct(), request);
    addProducts(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addCount(model, pageInfo.getTotal(), service.countTopic(q));
    addQ(model, q);
    addF(model, "product");
    return "search.ftl";
  }

  /**
   * 搜索主题结果页
   * 
   * @param q
   * @param p
   * @param model
   * @return
   * @throws SecurityException
   * @注意：解决请求参数乱码问题需修改TOMCAT <Connector> 添加 URIEncoding="UTF-8"
   */
  @RequestMapping(value = "/search/topic", method = RequestMethod.GET)
  public String searchTopic(@RequestParam String q, Pagination p, Model model,
      HttpServletRequest request) throws SecurityException {

    logger.info("用户输入搜索：{}", q);
    initPagination(p);
    TopicExample example = new TopicExample();
    TopicExample.Criteria criteria = example.createCriteria();
    criteria.andStateEqualTo((byte) 1); // 状态为启动
    
    if (StringUtils.isNotEmpty(q)) {
      criteria.andTitleLike("%" + q + "%"); // 模糊查询
    }

    PageInfo<Topic> pageInfo = service.findTopics(example, p);
    logger.info("用户搜索主题数：{}", pageInfo.getTotal());

    addHeader(model, props.getSeoSearchTopic(), request);
    addTopics(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addCount(model, service.countProduct(q), pageInfo.getTotal());
    addQ(model, q);
    addF(model, "topic");
    return "search.ftl";
  }

  /**
   * 根据类别获取产品
   * 
   * @param id 类别ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
  public String catProduct(@PathVariable Long id, Model model, Pagination p,
      HttpServletRequest request) throws SecurityException {

    initPagination(p);
    Category record = service.findCategory(id);
    ProductExample example = new ProductExample();
    ProductExample.Criteria criteria = example.createCriteria();
    criteria.andStateEqualTo((byte) 1); // 状态为启动
    criteria.andCidEqualTo(id);
    PageInfo<Product> pageInfo = service.findProducts(example, p);

    addHeader(model, record.getTitle(), request);
    addCategory(model, record);
    addProducts(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "product");
    return "category.ftl";
  }

  /**
   * 根据类别获取主题
   * 
   * @param id 类别ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/categories/{id}/topic", method = RequestMethod.GET)
  public String catTopic(@PathVariable Long id, Model model, Pagination p,
      HttpServletRequest request) throws SecurityException {

    initPagination(p);
    Category record = service.findCategory(id);
    TopicExample example = new TopicExample();
    TopicExample.Criteria criteria = example.createCriteria();
    criteria.andStateEqualTo((byte) 1); // 状态为启动
    criteria.andCidEqualTo(id);
    PageInfo<Topic> pageInfo = service.findTopics(example, p);

    addHeader(model, record.getTitle(), request);
    addCategory(model, record);
    addTopics(model, pageInfo.getList());
    addPager(model, pageInfo, getServletPath(request));
    addF(model, "topic");
    return "category.ftl";
  }

  /**
   * 关于我们
   * 
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/about", method = RequestMethod.GET)
  public String about(HttpServletRequest request, Model model) throws SecurityException {
    addHeader(model, props.getSeoAbout(), request);
    return "about.ftl";
  }

  /**
   * 更新产品点击率和获取响应URL
   * 
   * @param id 产品ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/{id}/hit", method = RequestMethod.GET)
  public String hitProduct(@PathVariable Long id, Model model) throws SecurityException {
    return redirect(service.updateProductHit(id));
  }

  /**
   * 推荐产品
   * 
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/recommend", method = RequestMethod.GET)
  public String recommend(Model model, HttpServletRequest request) throws SecurityException {
    addHeader(model, props.getSeoRecommend(), request);
    return "recommend.ftl";
  }
  
  /**
   * 创建主题
   * 
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/topics/new", method = RequestMethod.GET)
  public String createTopic(Model model, HttpServletRequest request) throws SecurityException {
    addHeader(model, "创建主题", request);
    addCategories(model, service.findAllCategories());
    return "topic-new.ftl";
  }

}
