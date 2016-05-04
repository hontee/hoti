package com.ikyer.site.front.cms;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ikyer.site.core.exception.SecurityException;
import com.ikyer.site.db.entity.DataGrid;
import com.ikyer.site.db.entity.Pagination;
import com.ikyer.site.db.entity.Product;
import com.ikyer.site.db.entity.Topic;
import com.ikyer.site.db.entity.TopicExample;
import com.ikyer.site.db.entity.TopicProduct;
import com.ikyer.site.db.entity.User;
import com.ikyer.site.db.entity.VUtil;
import com.ikyer.site.front.controller.BaseController;
import com.ikyer.site.front.vo.ResponseVO;
import com.ikyer.site.front.vo.TopicVO;
import com.ikyer.site.rest.BaseService;

@Controller
@RequestMapping("/cms/topics")
public class TopicCMS extends BaseController {

  private Logger logger = LoggerFactory.getLogger(TopicCMS.class);

  @Resource
  private BaseService service;

  /**
   * 主题管理首页
   * 
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/topics/index";
  }

  /**
   * 新建主题页
   * 
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/topics/new";
  }

  /**
   * 编辑主题页
   * 
   * @param id 主题ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    super.addRecord(model, service.findTopic(id));
    return "cms/topics/edit";
  }

  /**
   * 关注主题的用户列表页
   * 
   * @param id 主题ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/follow", method = RequestMethod.GET)
  public String followPage(@PathVariable Long id, Model model) throws SecurityException {
    super.addId(model, id);
    return "cms/topics/follow";
  }

  /**
   * 主题添加关联产品页
   * 
   * @param id 主题ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
  public String productPage(@PathVariable Long id, Model model) throws SecurityException {
    super.addId(model, id);
    return "cms/topics/product";
  }

  /**
   * 主题管理关联产品页
   * 
   * @param id 主题ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/manager", method = RequestMethod.GET)
  public String managerPage(@PathVariable Long id, Model model) throws SecurityException {
    super.addId(model, id);
    return "cms/topics/manager";
  }

  /**
   * 主题详情页
   * 
   * @param id
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    super.addRecord(model, service.findTopic(id));
    return "cms/topics/view";
  }

  /**
   * 主题数据列表，支持分页和查询
   * 
   * @param title 标题
   * @param type 类别
   * @param state 状态
   * @param p 分页组件
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Topic> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Byte type, @RequestParam(required = false) Byte state,
      Pagination p) throws SecurityException {

    TopicExample example = new TopicExample();
    TopicExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (VUtil.assertBaseState(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Topic> pageInfo = service.findTopics(example, p);
    return new DataGrid<>(pageInfo);
  }

  /**
   * 主题关联的产品列表
   * 
   * @param id
   * @param title
   * @param state
   * @param p
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/products")
  public @ResponseBody DataGrid<Product> findTopicProducts(@PathVariable Long id,
      @RequestParam(required = false) String title, @RequestParam(required = false) Byte state,
      Pagination p) throws SecurityException {

    /* 产品信息过滤 */
    if (StringUtils.isEmpty(title)) {
      title = null;
    }
    if (!VUtil.assertBaseState(state)) {
      state = null;
    }

    TopicProduct tp = new TopicProduct(id);
    tp.setTitle(title);
    tp.setState(state);
    
    PageInfo<Product> pageInfo = service.findTopicProducts(tp, p);
    return new DataGrid<>(pageInfo);
  }

  /**
   * 关注主题的用户列表
   * 
   * @param id
   * @param name
   * @param userType
   * @param state
   * @param p
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/users")
  public @ResponseBody DataGrid<User> findTopicUsers(@PathVariable Long id,
      @RequestParam(required = false) String name, @RequestParam(required = false) Byte type,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    /* 用户信息过滤 */
    if (StringUtils.isEmpty(name)) {
      name = null;
    }
    if (!VUtil.assertBaseState(state)) {
      state = null;
    }
    if (!VUtil.assertUserType(type)) {
      type = null;
    }

    PageInfo<User> pageInfo = service.findTopicUsers(id, name, type, state, p);
    return new DataGrid<>(pageInfo);
  }

  /**
   * 新建主题
   * 
   * @param vo
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody ResponseVO addTopic(TopicVO vo, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台添加主题: {}", JSON.toJSONString(vo));
    service.addTopic(vo);
    return buildResponse();
  }

  /**
   * 主题批量添加关联产品
   * 
   * @param ids
   * @param id
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/product", method = RequestMethod.POST)
  public @ResponseBody ResponseVO addProducts(@RequestParam Long[] ids, @PathVariable Long id,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台主题批量添加产品: {}, {}", id, ids);
    service.addTopicProduct(id, ids);
    return buildResponse();
  }

  /**
   * 主题批量移除关联产品
   * 
   * @param ids
   * @param id
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/manager", method = RequestMethod.POST)
  public @ResponseBody ResponseVO deleteProducts(@RequestParam Long[] ids, @PathVariable Long id,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台主题批量移除产品: {}, {}", id, ids);
    service.deleteTopicProduct(id, ids);
    return buildResponse();
  }

  /**
   * 删除主题
   * 
   * @param id
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody ResponseVO deleteTopic(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台删除主题: {}", id);
    service.deleteTopic(id);
    return buildResponse();
  }

  /**
   * 编辑主题
   * 
   * @param id
   * @param vo
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody ResponseVO editTopic(@PathVariable Long id, TopicVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台编辑主题：{}, {}", id, JSON.toJSONString(vo));
    service.updateTopic(id, vo);
    return buildResponse();
  }

  /**
   * 批量精选主题
   * 
   * @param ids
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/pick", method = RequestMethod.POST)
  public @ResponseBody ResponseVO pickTopic(@RequestParam Long[] ids, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台主题精选: {}", ids);
    service.pickTopic(ids);
    return buildResponse();
  }

  /**
   * 批量取消精选主题
   * 
   * @param ids
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/unpick", method = RequestMethod.POST)
  public @ResponseBody ResponseVO unpickTopic(@RequestParam Long[] ids, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台主题取消精选: {}", ids);
    service.unpickTopic(ids);
    return buildResponse();
  }

}
