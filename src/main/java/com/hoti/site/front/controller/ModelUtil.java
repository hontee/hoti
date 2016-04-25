package com.hoti.site.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.core.security.AuthzUtil;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.PagerUtil;
import com.hoti.site.db.entity.Product;
import com.hoti.site.db.entity.Topic;
import com.hoti.site.rest.BaseService;

/**
 * 模块工具
 * 
 * @author larry.qi
 *
 */
public class ModelUtil {

  /**
   * 初始信息
   * 
   * @param model
   * @param title
   * @param ds
   * @throws SecurityException
   */
  public static void addHeader(Model model, String title, HttpServletRequest request)
      throws SecurityException {
    addHeader(model, title, null, null, request);
  }

  /**
   * 初始信息
   * 
   * @param model
   * @param title
   * @param keywords
   * @param desc
   * @param ds
   * @throws SecurityException
   */
  public static void addHeader(Model model, String title, String keywords, String desc,
      HttpServletRequest request) throws SecurityException {
    model.addAttribute("title", title);
    model.addAttribute("keywords", keywords);
    model.addAttribute("description", desc);
    model.addAttribute("user", AuthzUtil.isAuthorized() ? AuthzUtil.getUsername() : null);
    
    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    BaseService service = wac.getBean(BaseService.class);
    ModelUtil.addCategories(model, service.findAllCategories());
  }

  /**
   * 过滤参数
   * 
   * @param model
   * @param f
   */
  public static void addF(Model model, String f) {
    model.addAttribute("f", f);
  }

  /**
   * 查询关键字
   * 
   * @param model
   * @param q
   */
  public static void addQ(Model model, String q) {
    model.addAttribute("q", q);
  }

  /**
   * ID
   * 
   * @param model
   * @param id
   */
  public static void addId(Model model, Long id) {
    model.addAttribute("id", id);
  }

  /**
   * 对象记录
   * 
   * @param model
   * @param pageInfo
   * @param url
   */
  public static void addRecord(Model model, Object record) {
    model.addAttribute("record", record);
  }

  /**
   * 分页
   * 
   * @param model
   * @param pageInfo
   * @param url
   */
  public static void addPager(Model model, PageInfo<?> pageInfo, String url) {
    model.addAttribute("page", PagerUtil.of(pageInfo, url));
  }

  /**
   * 类别
   * 
   * @param model
   * @param group
   */
  public static void addCategory(Model model, Category category) {
    model.addAttribute("category", category);
  }

  /**
   * 所有类别
   * 
   * @param model
   * @param group
   */
  public static void addCategories(Model model, List<Category> list) {
    model.addAttribute("categories", list);
  }

  /**
   * 产品列表
   * 
   * @param model
   * @param list
   */
  public static void addProducts(Model model, List<Product> list) {
    model.addAttribute("products", list);
  }

  /**
   * 主题
   * 
   * @param model
   * @param topic
   */
  public static void addTopic(Model model, Topic topic) {
    model.addAttribute("topic", topic);
  }

  /**
   * 主题列表
   * 
   * @param model
   * @param list
   */
  public static void addTopics(Model model, List<Topic> list) {
    model.addAttribute("topics", list);
  }
  
  /**
   * 查询统计产品数和主题数
   * 
   * @param model
   * @param product 产品数
   * @param topic 主题数
   */
  public static void addCount(Model model, long product, long topic) {
    model.addAttribute("product", product);
    model.addAttribute("topic", topic);
  }

  private ModelUtil() {}

}
