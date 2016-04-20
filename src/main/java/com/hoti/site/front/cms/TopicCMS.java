package com.hoti.site.front.cms;

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
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.DataGrid;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.Product;
import com.hoti.site.db.entity.SiteResponse;
import com.hoti.site.db.entity.StateUtil;
import com.hoti.site.db.entity.Topic;
import com.hoti.site.db.entity.TopicExample;
import com.hoti.site.db.entity.User;
import com.hoti.site.db.entity.UserTypeUtil;
import com.hoti.site.front.controller.ModelUtil;
import com.hoti.site.front.controller.SiteUtil;
import com.hoti.site.front.vo.GroupVO;
import com.hoti.site.rest.BaseService;

@Controller
@RequestMapping("/cms/topics")
public class TopicCMS {

  private Logger logger = LoggerFactory.getLogger(TopicCMS.class);

  @Resource
  private BaseService service;

  /**
   * 主题管理首页
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
   * @param id 主题ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    ModelUtil.addRecord(model, service.findTopic(id));
    return "cms/topics/edit";
  }

  /**
   * 关注主题的用户列表页
   * @param id 主题ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/follow", method = RequestMethod.GET)
  public String followPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("id", id);
    return "cms/topics/follow";
  }

  /**
   * 主题添加关联产品页
   * @param id 主题ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/bookmark", method = RequestMethod.GET)
  public String bookmarkPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("id", id);
    return "cms/topics/product";
  }

  /**
   * 主题管理关联产品页
   * @param id 主题ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/manager", method = RequestMethod.GET)
  public String managerPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("id", id);
    return "cms/topics/manager";
  }

  /**
   * 主题详情页
   * @param id
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    ModelUtil.addRecord(model, service.findTopic(id));
    return "cms/topics/view";
  }

  /**
   * 主题数据列表，支持分页和查询
   * @param title 标题
   * @param cid 类别
   * @param type 类别
   * @param state 状态
   * @param p 分页组件
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Topic> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(defaultValue = "0") Long cid, @RequestParam(required = false) Byte type,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    TopicExample example = new TopicExample();
    TopicExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (cid > 0) {
      criteria.andCidEqualTo(cid);
    }

    if (StateUtil.baseValidate(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Topic> pageInfo = service.findTopics(example, p);
    return new DataGrid<>(pageInfo);
  }

  /**
   * 主题关联的产品列表
   * @param id
   * @param title
   * @param category
   * @param state
   * @param p
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/bookmarks")
  public @ResponseBody DataGrid<Product> groupBookmarks(@PathVariable Long id,
      @RequestParam(required = false) String title, @RequestParam(defaultValue = "0") Long cid,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {
    
    /* 产品信息过滤 */
    if (StringUtils.isEmpty(title)) {
      title = null;
    }
    if (cid <= 0) {
      cid = null;
    }
    if (!StateUtil.baseValidate(state)) {
      state = null;
    }
    
    PageInfo<Product> pageInfo = service.findTopicProducts(id, title, cid, state, p);
    return new DataGrid<>(pageInfo);
  }

  /**
   * 关注主题的用户列表
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
  public @ResponseBody DataGrid<User> followUsers(@PathVariable Long id,
      @RequestParam(required = false) String name, @RequestParam(required = false) Byte type,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    /* 用户信息过滤 */
    if (StringUtils.isEmpty(name)) {
      name = null;
    }
    if (!StateUtil.userValidate(state)) {
      state = null;
    }
    if (!UserTypeUtil.validate(type)) {
      type = null;
    }
    
    PageInfo<User> pageInfo = service.findTopicUsers(id, name, type, state, p);
    return new DataGrid<>(pageInfo);
  }

  /**
   * 新建主题
   * @param vo
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody SiteResponse add(GroupVO vo, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台添加主题: {}", JSON.toJSONString(vo));
    service.addTopic(vo);
    return SiteUtil.ok();
  }

  /**
   * 主题批量添加关联产品
   * @param ids
   * @param id
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/bookmark", method = RequestMethod.POST)
  public @ResponseBody SiteResponse addBookmark(@RequestParam Long[] ids, @PathVariable Long id,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台主题批量添加产品: {}, {}", id, ids);
    service.addTopicProduct(id, ids);
    return SiteUtil.ok();
  }

  /**
   * 主题批量移除关联产品
   * @param ids
   * @param id
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/manager", method = RequestMethod.POST)
  public @ResponseBody SiteResponse removeBookmarks(@RequestParam Long[] ids, @PathVariable Long id,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台主题批量移除产品: {}, {}", id, ids);
    service.deleteTopicProduct(id, ids);
    return SiteUtil.ok();
  }

  /**
   * 删除主题
   * @param id
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台删除主题: {}", id);
    service.deleteTopic(id);
    return SiteUtil.ok();
  }

  /**
   * 编辑主题
   * @param id
   * @param vo
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody SiteResponse edit(@PathVariable Long id, GroupVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台编辑主题：{}, {}", id, JSON.toJSONString(vo));
    service.updateTopic(id, vo);
    return SiteUtil.ok();
  }
  
  /**
   * 批量精选主题
   * @param ids
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/pick", method = RequestMethod.POST)
  public @ResponseBody SiteResponse pick(@RequestParam Long[] ids,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台主题精选: {}", ids);
    service.pickTopic(ids);
    return SiteUtil.ok();
  }
  
  /**
   * 批量取消精选主题
   * @param ids
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/unpick", method = RequestMethod.POST)
  public @ResponseBody SiteResponse unpick(@RequestParam Long[] ids,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台主题取消精选: {}", ids);
    service.unpickTopic(ids);
    return SiteUtil.ok();
  }

  /**
   * 统计任务
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/count/task", method = RequestMethod.POST)
  public @ResponseBody SiteResponse countTask(HttpServletRequest request) throws SecurityException {
    logger.info("后台统计主题数据");
    // TODO
    return SiteUtil.ok();
  }

}
