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
import com.hoti.site.db.entity.SiteResponse;
import com.hoti.site.db.entity.StateUtil;
import com.hoti.site.db.entity.User;
import com.hoti.site.db.entity.UserExample;
import com.hoti.site.db.entity.UserTypeUtil;
import com.hoti.site.front.controller.SiteUtil;
import com.hoti.site.front.vo.UserVO;
import com.hoti.site.rest.BaseService;

@Controller
@RequestMapping("/cms/users")
public class UserCMS {

  private Logger logger = LoggerFactory.getLogger(UserCMS.class);

  @Resource
  private BaseService service;

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/users/index";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/users/new";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/password", method = RequestMethod.GET)
  public String passwordPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", service.findUser(id));
    return "cms/users/password";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", service.findUser(id));
    return "cms/users/edit";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", service.findUser(id));
    return "cms/users/view";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<User> dataGrid(@RequestParam(required = false) String name,
      @RequestParam(required = false) Byte userType, @RequestParam(required = false) Byte state,
      Pagination p) throws SecurityException {

    UserExample example = new UserExample();
    UserExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(name)) {
      criteria.andNameLike("%" + name + "%"); // 模糊查询
    }

    if (UserTypeUtil.validate(userType)) {
      criteria.andTypeEqualTo(userType);
    }

    if (StateUtil.validate(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<User> pageInfo = service.findUsers(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody SiteResponse add(UserVO vo, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台添加用户: {}", JSON.toJSONString(vo));
    service.addUser(vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台删除用户：{}", id);
    service.deleteUser(id);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody SiteResponse edit(@PathVariable Long id, UserVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台编辑用户: {}, {}", id, JSON.toJSONString(vo));
    service.updateUser(id, vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/password", method = RequestMethod.POST)
  public @ResponseBody SiteResponse password(@PathVariable Long id, @RequestParam String password,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台修改用户密码: {}", id);
    service.updateUser(id, password);
    return SiteUtil.ok();
  }
}
