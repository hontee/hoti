package com.hoti.site.front.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.Product;
import com.hoti.site.db.entity.ProductExample;
import com.hoti.site.db.entity.Topic;
import com.hoti.site.db.entity.TopicExample;
import com.hoti.site.rest.BaseService;

@Controller
@Scope("prototype")
public class CategoryController {

  @Resource
  private BaseService service;

  /**
   * 类别首页
   * 
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/categories", method = RequestMethod.GET)
  public String findAllCategories(Model model) throws SecurityException {
    ModelUtil.addHeader(model, "红提 | 所有类别");
    List<Category> list = service.findAllCategories();
    ModelUtil.addCategories(model, list);
    return "category.ftl";
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
  public String findProducts(@PathVariable Long id, Model model, Pagination p,
      HttpServletRequest request) throws SecurityException {

    System.err.println("======================" + request.getContextPath());
    
    p.initFrontRows();
    Category record = service.findCategory(id);
    ModelUtil.addHeader(model, record.getTitle().concat(" | 红提"));

    ProductExample example = new ProductExample();
    example.createCriteria().andCidEqualTo(id);
    PageInfo<Product> pageInfo = service.findProducts(example, p);

    ModelUtil.addCategory(model, record);
    ModelUtil.addPager(model, pageInfo, "/categories/" + id);
    ModelUtil.addProducts(model, pageInfo.getList());
    ModelUtil.addF(model, "product");
    
    return "category-list.ftl";
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
  public String findTopics(@PathVariable Long id, Model model, Pagination p,
      HttpServletRequest request) throws SecurityException {

    System.err.println("======================" + request.getContextPath());
    
    p.initFrontRows();
    Category record = service.findCategory(id);
    ModelUtil.addHeader(model, record.getTitle().concat(" | 红提"));

    TopicExample example = new TopicExample();
    example.createCriteria().andCidEqualTo(id);
    PageInfo<Topic> pageInfo = service.findTopics(example, p);

    ModelUtil.addCategory(model, record);
    ModelUtil.addPager(model, pageInfo, "/categories/" + id + "/topic");
    ModelUtil.addTopics(model, pageInfo.getList());
    ModelUtil.addF(model, "topic");

    return "category-list.ftl";
  }

}
