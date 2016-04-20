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
import com.hoti.site.front.controller.SiteUtil;
import com.hoti.site.front.vo.GroupVO;
import com.hoti.site.rest.BaseService;

@Controller
@RequestMapping("/cms/topics")
public class TopicCMS {

  private Logger logger = LoggerFactory.getLogger(TopicCMS.class);

  @Resource
  private BaseService service;

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/groups/index";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/groups/new";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", service.findTopic(id));
    return "cms/groups/edit";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/follow", method = RequestMethod.GET)
  public String followPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("id", id);
    return "cms/groups/follow";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/bookmark", method = RequestMethod.GET)
  public String bookmarkPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("id", id);
    return "cms/groups/bookmark";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/manager", method = RequestMethod.GET)
  public String managerPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("id", id);
    return "cms/groups/manager";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", service.findTopic(id));
    return "cms/groups/view";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Topic> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Long category, @RequestParam(required = false) String gtype,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    TopicExample example = new TopicExample();
    TopicExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (category != null && category > 0) {
      criteria.andCidEqualTo(category);
    }

    if (StateUtil.userValidate(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Topic> pageInfo = service.findTopics(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/bookmarks")
  public @ResponseBody DataGrid<Product> groupBookmarks(@PathVariable Long id,
      @RequestParam(required = false) String title, @RequestParam(required = false) Long category,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {
    PageInfo<Product> pageInfo = service.findTopicProducts(id, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/follows")
  public @ResponseBody DataGrid<User> followUsers(@PathVariable Long id,
      @RequestParam(required = false) String name, @RequestParam(required = false) Byte userType,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {
    PageInfo<User> pageInfo = service.findTopicUsers(id, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody SiteResponse add(GroupVO vo, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台添加群组: {}", JSON.toJSONString(vo));
    service.addTopic(vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/bookmark", method = RequestMethod.POST)
  public @ResponseBody SiteResponse addBookmark(@RequestParam Long[] ids, @PathVariable Long id,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台群组批量添加站点: {}, {}", id, ids);
    service.addTopicProduct(id, ids);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/manager", method = RequestMethod.POST)
  public @ResponseBody SiteResponse removeBookmarks(@RequestParam Long[] ids, @PathVariable Long id,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台群组批量移除站点: {}, {}", id, ids);
    service.deleteTopicProduct(id, ids);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台删除群组: {}", id);
    service.deleteTopic(id);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody SiteResponse edit(@PathVariable Long id, GroupVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台编辑群组：{}, {}", id, JSON.toJSONString(vo));
    service.updateTopic(id, vo);
    return SiteUtil.ok();
  }
  
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/pick", method = RequestMethod.POST)
  public @ResponseBody SiteResponse pick(@RequestParam Long[] ids,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台群组精选: {}", ids);
    service.pickTopic(ids);
    return SiteUtil.ok();
  }
  
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/unpick", method = RequestMethod.POST)
  public @ResponseBody SiteResponse unpick(@RequestParam Long[] ids,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台群组取消精选: {}", ids);
    service.unpickTopic(ids);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/count/task", method = RequestMethod.POST)
  public @ResponseBody SiteResponse countTask(HttpServletRequest request) throws SecurityException {
    logger.info("后台统计群组数据");
    // TODO
    return SiteUtil.ok();
  }

}
