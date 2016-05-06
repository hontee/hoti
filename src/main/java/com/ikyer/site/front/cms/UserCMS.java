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
import com.ikyer.site.db.entity.User;
import com.ikyer.site.db.entity.UserExample;
import com.ikyer.site.db.entity.VUtil;
import com.ikyer.site.front.BaseController;
import com.ikyer.site.front.vo.ResponseVO;
import com.ikyer.site.front.vo.UserVO;
import com.ikyer.site.rest.BaseService;

@Controller
@RequestMapping("/cms/users")
public class UserCMS extends BaseController {

  private Logger logger = LoggerFactory.getLogger(UserCMS.class);

  @Resource
  private BaseService service;

  /**
   * 用户管理首页
   * 
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/users/index";
  }

  /**
   * 新建用户页
   * 
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/users/new";
  }

  /**
   * 修改用户密码页
   * 
   * @param id 用户ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/password", method = RequestMethod.GET)
  public String passwordPage(@PathVariable Long id, Model model) throws SecurityException {
    super.addRecord(model, service.findUser(id));
    return "cms/users/password";
  }

  /**
   * 编辑用户页
   * 
   * @param id 用户ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    super.addRecord(model, service.findUser(id));
    return "cms/users/edit";
  }

  /**
   * 用户详情页
   * 
   * @param id 用户ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    super.addRecord(model, service.findUser(id));
    return "cms/users/view";
  }

  /**
   * 用户数据列表，支持分页的查询
   * 
   * @param name 用户名
   * @param type 用户类型 1=普通用户 2=管理员
   * @param state 状态 0=禁用 1=启用 2=锁定 3=删除
   * @param p 分页组件
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<User> dataGrid(@RequestParam(required = false) String name,
      @RequestParam(required = false) Byte type, @RequestParam(required = false) Byte state,
      Pagination p) throws SecurityException {

    UserExample example = new UserExample();
    UserExample.Criteria criteria = example.createCriteria();

    /* 用户名 模糊查询 */
    if (StringUtils.isNotBlank(name)) {
      criteria.andNameLike("%" + name + "%");
    }

    /* 验证用户类型 */
    if (VUtil.assertUserType(type)) {
      criteria.andTypeEqualTo(type);
    }

    /* 验证用户状态 */
    if (VUtil.assertUserState(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<User> pageInfo = service.findUsers(example, p);
    return new DataGrid<>(pageInfo);
  }

  /**
   * 新建用户
   * 
   * @param vo 请求参数
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody ResponseVO addUser(UserVO vo, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台添加用户: {}", JSON.toJSONString(vo));
    service.addUser(vo);
    return buildResponse();
  }

  /**
   * 删除用户
   * 
   * @param id 用户ID
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody ResponseVO deleteUser(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台删除用户：{}", id);
    service.deleteUser(id);
    return buildResponse();
  }

  /**
   * 编辑用户
   * 
   * @param id 用户ID
   * @param vo 请求参数
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody ResponseVO editUser(@PathVariable Long id, UserVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台编辑用户: {}, {}", id, JSON.toJSONString(vo));
    service.updateUser(id, vo);
    return buildResponse();
  }

  /**
   * 修改用户密码
   * 
   * @param id 用户ID
   * @param password 新密码
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/password", method = RequestMethod.POST)
  public @ResponseBody ResponseVO updatePassword(@PathVariable Long id,
      @RequestParam String password, HttpServletRequest request) throws SecurityException {
    logger.info("后台修改用户密码: {}", id);
    service.updateUser(id, password);
    return buildResponse();
  }

}
