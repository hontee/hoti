package com.hoti.site.front.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.Product;
import com.hoti.site.db.entity.ProductExample;
import com.hoti.site.db.entity.Topic;
import com.hoti.site.db.entity.TopicExample;
import com.hoti.site.rest.BaseService;

@Controller
@Scope("prototype")
public class SearchController {

  private Logger logger = LoggerFactory.getLogger(TopicController.class);

  @Resource
  private BaseService service;

  /**
   * @WebPage 搜索结果页
   * @param q
   * @param p
   * @param model
   * @return
   * @throws SecurityException
   * @注意：解决请求参数乱码问题需修改TOMCAT <Connector> 添加 URIEncoding="UTF-8"
   */
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public String search(@RequestParam String q, @RequestParam(defaultValue = "site") String f,
      Pagination p, HttpServletRequest request, Model model) throws SecurityException {

    logger.info("用户输入搜索：{}, 过滤条件：{}", q, f);

    ModelUtil.addHeader(model, "红提 | 搜索结果");
    ModelUtil.addQ(model, q);

    p.initFrontRows();

    if ("site".equals(f)) { // 查询书签
      searchByBookmark(q, p, model);
    } else { // 查询主题
      searchByGroup(q, p, model);
    }

    return "search.ftl";
  }

  /**
   * 书签搜索
   * 
   * @throws SecurityException
   */
  private void searchByBookmark(String q, Pagination p, Model model) throws SecurityException {
    ProductExample example = new ProductExample();
    ProductExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotEmpty(q)) {
      criteria.andTitleLike("%" + q + "%"); // 模糊查询
    }

    PageInfo<Product> pageInfo = service.findProducts(example, p);
    List<Product> list = pageInfo.getList();

    logger.info("用户搜索结果数：{}", pageInfo.getTotal());

    ModelUtil.addPager(model, pageInfo, "/search?q=" + q);
    ModelUtil.addF(model, "site");
    ModelUtil.addBookmarks(model, list);
    model.addAttribute("bcount", pageInfo.getTotal());
    model.addAttribute("gcount", 0 /*gs.count(q)*/);
  }

  /**
   * 主题搜索
   */
  private void searchByGroup(String q, Pagination p, Model model) throws SecurityException {
    TopicExample example = new TopicExample();
    TopicExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotEmpty(q)) {
      criteria.andTitleLike("%" + q + "%"); // 模糊查询
    }

    PageInfo<Topic> pageInfo = service.findTopics(example, p);
    List<Topic> list = pageInfo.getList();

    logger.info("用户搜索结果数：{}", pageInfo.getTotal());

    ModelUtil.addPager(model, pageInfo, "/search?f=group&q=" + q);
    ModelUtil.addF(model, "group");
    ModelUtil.addGroups(model, list);
    model.addAttribute("bcount", 0 /*bs.count(q)*/);
    model.addAttribute("gcount", pageInfo.getTotal());
  }
}
