package com.hoti.site.front.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
  
  @RequestMapping(value = "/category", method = RequestMethod.GET)
  public String  cate(Model model) throws SecurityException {
    ModelUtil.addHeader(model, "红提 | 所有分类");
    List<Category> list = service.findAllCategories();
    model.addAttribute("cates", list);
    return "category.ftl";
  }

  /**
   * @WebPage 分类下的所有站点 （支持分页）
   * @param id
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
  public String cateBm(@PathVariable Long id, Model model,
      @RequestParam(defaultValue = "site") String f, Pagination p) throws SecurityException {

    p.initFrontRows();
    ModelUtil.addHeader(model, "红提 | 所有分类");

    if ("site".equals(f)) { // 查询书签
      byBookmark(id, p, model);
    } else { // 查询主题
      byGroup(id, p, model);
    }

    return "category-list.ftl";
  }

  /**
   * 书签
   * 
   * @throws SecurityException
   */
  private void byBookmark(long id, Pagination p, Model model) throws SecurityException {
    Category record = service.findCategory(id);

    ProductExample example = new ProductExample();
    example.createCriteria().andCidEqualTo(id);
    PageInfo<Product> pageInfo = service.findProducts(example, p);

    ModelUtil.addCategory(model, record);
    ModelUtil.addPager(model, pageInfo, "/category/" + id + "?f=site");
    ModelUtil.addBookmarks(model, pageInfo.getList());
    ModelUtil.addF(model, "site");
  }

  /**
   * 主题
   */
  private void byGroup(long id, Pagination p, Model model) throws SecurityException {
    Category record = service.findCategory(id);

    TopicExample example = new TopicExample();
    example.createCriteria().andCidEqualTo(id);
    PageInfo<Topic> pageInfo = service.findTopics(example, p);

    ModelUtil.addCategory(model, record);
    ModelUtil.addPager(model, pageInfo, "/category/" + id + "?f=group");
    ModelUtil.addGroups(model, pageInfo.getList());
    ModelUtil.addF(model, "group");
  }

}
