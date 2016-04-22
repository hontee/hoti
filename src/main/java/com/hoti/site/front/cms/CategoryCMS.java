package com.hoti.site.front.cms;

import java.util.ArrayList;
import java.util.List;

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
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.CategoryExample;
import com.hoti.site.db.entity.ComboBox;
import com.hoti.site.db.entity.DataGrid;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.VUtil;
import com.hoti.site.front.controller.BaseController;
import com.hoti.site.front.controller.ModelUtil;
import com.hoti.site.front.vo.CategoryVO;
import com.hoti.site.front.vo.ResponseVO;
import com.hoti.site.rest.BaseService;

@Controller
@RequestMapping("/cms/categories")
public class CategoryCMS extends BaseController {

  private Logger logger = LoggerFactory.getLogger(CategoryCMS.class);

  @Resource
  private BaseService service;

  /**
   * 类别管理首页
   * 
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/categories/index";
  }

  /**
   * 新建类别页
   * 
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/categories/new";
  }

  /**
   * 编辑类别页
   * 
   * @param id
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    ModelUtil.addRecord(model, service.findCategory(id));
    return "cms/categories/edit";
  }

  /**
   * 查看类别详情页
   * 
   * @param id
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    ModelUtil.addRecord(model, service.findCategory(id));
    return "cms/categories/view";
  }

  /**
   * 类别数据列表，支持查询和分页
   * 
   * @param title 标题
   * @param state 状态 0=禁用 1=启用
   * @param p 分页组件
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Category> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    CategoryExample example = new CategoryExample();
    CategoryExample.Criteria criteria = example.createCriteria();

    /* 标题 模糊查询 */
    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%");
    }

    /* 验证查询状态 */
    if (VUtil.assertBaseState(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Category> pageInfo = service.findCategories(example, p);
    return new DataGrid<>(pageInfo);
  }

  /**
   * 所有类别列表
   * 
   * @param q 如果q=all则添加 [全部类别]
   * @param fid 过滤掉的ID，当修改时不能指定当前ID作为父ID
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/datalist")
  public @ResponseBody List<ComboBox> datalist(@RequestParam(required = false) String q,
      @RequestParam(defaultValue = "0") Long fid) throws SecurityException {
    List<Category> list = service.findAllCategories();
    List<ComboBox> boxes = new ArrayList<>();

    /* 如果传入 ?q=all 则返回全部类别 */
    if ("all".equals(q)) {
      boxes.add(new ComboBox(0L, "全部类别"));
    }

    list.forEach((c) -> {
      /* 过滤掉的ID，当修改时不能指定当前ID作为父ID */
      if (fid != c.getId()) {
        boxes.add(new ComboBox(c.getId(), c.getTitle()));
      }
    });
    return boxes;
  }

  /**
   * 新建类别
   * 
   * @param vo 请求参数
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody ResponseVO addCategory(CategoryVO vo, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台添加类别: {}", JSON.toJSONString(vo));
    service.addCategory(vo);
    return buildResponse();
  }

  /**
   * 删除类别
   * 
   * @param id 类别ID
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody ResponseVO deleteCategory(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台删除类别: {}", id);
    service.deleteCategory(id);
    return buildResponse();
  }

  /**
   * 编辑类别
   * 
   * @param id 类别ID
   * @param vo 请求参数
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody ResponseVO editCategory(@PathVariable Long id, CategoryVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台编辑类别：{}, {}", id, JSON.toJSONString(vo));
    service.updateCategory(id, vo);
    return buildResponse();
  }

}
