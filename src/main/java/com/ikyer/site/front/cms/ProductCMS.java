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
import com.ikyer.site.db.entity.ProductExample;
import com.ikyer.site.db.entity.User;
import com.ikyer.site.db.entity.VUtil;
import com.ikyer.site.front.BaseController;
import com.ikyer.site.front.vo.ProductVO;
import com.ikyer.site.front.vo.ResponseVO;
import com.ikyer.site.rest.BaseService;

@Controller
@RequestMapping("/cms/products")
public class ProductCMS extends BaseController {

  private Logger logger = LoggerFactory.getLogger(ProductCMS.class);

  @Resource
  private BaseService service;

  /**
   * 产品管理首页
   * 
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/products/index";
  }

  /**
   * 新建产品页
   * 
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/products/new";
  }

  /**
   * 编辑产品页
   * 
   * @param id 产品ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    super.addRecord(model, service.findProduct(getUserId(), id));
    return "cms/products/edit";
  }

  /**
   * 关注产品的用户列表页
   * 
   * @param id 产品ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/follow", method = RequestMethod.GET)
  public String followPage(@PathVariable Long id, Model model) throws SecurityException {
    super.addId(model, id);
    return "cms/products/follow";
  }

  /**
   * 产品详情页
   * 
   * @param id 产品ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    super.addRecord(model, service.findProduct(getUserId(), id));
    return "cms/products/view";
  }

  /**
   * 产品数据列表，支持分页和查询
   * 
   * @param title 标题
   * @param state 状态
   * @param p 分页组件
   * @return
   * @throws Exception
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Product> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Byte state, Pagination p) throws Exception {

    ProductExample example = new ProductExample();
    ProductExample.Criteria criteria = example.createCriteria();

    /* 标题 模糊查询 */
    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%");
    }

    /* 验证产品状态 */
    if (VUtil.assertBaseState(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Product> pageInfo = service.findProducts(getUserId(), example, p);
    return new DataGrid<>(pageInfo);
  }

  /**
   * 关注产品的用户列表
   * 
   * @param id 产品ID
   * @param name 用户名
   * @param type 用户类型 1=普通用户 2=管理员
   * @param state 用户状态 0=禁用 1=启用 2=锁定3=已删除
   * @param p 分页组件
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/users")
  public @ResponseBody DataGrid<User> findProductUsers(@PathVariable Long id,
      @RequestParam(required = false) String name, @RequestParam(required = false) Byte type,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    User user = new User();
    
    /* 用户信息过滤 */
    if (StringUtils.isNotEmpty(name)) {
      user.setName(name);
    }
    if (VUtil.assertBaseState(state)) {
      user.setState(state);
    }
    if (VUtil.assertUserType(type)) {
      user.setType(type);
    }

    PageInfo<User> pageInfo = service.findUsersByProduct(id, user, p);
    return new DataGrid<>(pageInfo);
  }

  /**
   * 新建产品
   * 
   * @param vo 请求参数
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody ResponseVO addProduct(ProductVO vo, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台添加产品: {}", JSON.toJSONString(vo));
    service.addProduct(vo);
    return buildResponse();
  }

  /**
   * 删除产品
   * 
   * @param id 产品ID
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody ResponseVO deleteProduct(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台删除产品: {}", id);
    service.deleteProduct(id);
    return buildResponse();
  }

  /**
   * 编辑产品
   * 
   * @param id 产品ID
   * @param vo 请求参数
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody ResponseVO editProduct(@PathVariable Long id, ProductVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台编辑产品: {}, {}", id, JSON.toJSONString(vo));
    service.updateProduct(id, vo);
    return buildResponse();
  }

  /**
   * 批量精选产品
   * 
   * @param ids 产品IDs
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/pick", method = RequestMethod.POST)
  public @ResponseBody ResponseVO pickProduct(@RequestParam Long[] ids, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台产品精选: {}", ids);
    service.pickProduct(ids);
    return buildResponse();
  }

  /**
   * 批量取消精选产品IDs
   * 
   * @param ids 产品IDs
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/unpick", method = RequestMethod.POST)
  public @ResponseBody ResponseVO unpickProduct(@RequestParam Long[] ids,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台产品取消精选: {}", ids);
    service.unpickProduct(ids);
    return buildResponse();
  }

}
