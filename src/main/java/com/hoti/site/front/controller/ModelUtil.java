package com.hoti.site.front.controller;

import java.util.List;

import org.springframework.ui.Model;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.core.security.AuthzUtil;
import com.hoti.site.db.entity.Bookmark;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.Group;
import com.hoti.site.db.entity.PagerUtil;

/**
 * 模块工具
 * 
 * @author larry.qi
 *
 */
public class ModelUtil {

  /**
   * 设置初始模块
   * 
   * @param model
   * @param title
   * @param ds
   * @throws SecurityException
   */
  public static void addHeader(Model model, String title)
      throws SecurityException {
    addHeader(model, title, null, null);
  }

  /**
   * 设置初始模块信息
   * 
   * @param model
   * @param title
   * @param keywords
   * @param desc
   * @param ds
   * @throws SecurityException
   */
  public static void addHeader(Model model, String title, String keywords, String desc) throws SecurityException {
    model.addAttribute("title", title);
    model.addAttribute("keywords", keywords);
    model.addAttribute("description", desc);
    model.addAttribute("user", AuthzUtil.isAuthorized() ? AuthzUtil.getUsername() : null);
  }

  /**
   * 设置查询模块
   * 
   * @param model
   * @param q
   */
  public static void addQ(Model model, String q) {
    model.addAttribute("q", q);
  }

  /**
   * 设置过滤参数
   * 
   * @param model
   * @param f
   */
  public static void addF(Model model, String f) {
    model.addAttribute("f", f);
  }

  /**
   * 设置分页模块
   * 
   * @param model
   * @param pageInfo
   * @param url
   */
  public static void addPager(Model model, PageInfo<?> pageInfo, String url) {
    model.addAttribute("page", PagerUtil.of(pageInfo, url));
  }

  /**
   * 设置分类模块
   * 
   * @param model
   * @param group
   */
  public static void addCategory(Model model, Category category) {
    model.addAttribute("category", category);
  }

  /**
   * 设置主题模块
   * 
   * @param model
   * @param group
   */
  public static void addGroup(Model model, Group group) {
    model.addAttribute("group", group);
  }

  /**
   * 设置主题列表
   * 
   * @param model
   * @param list
   */
  public static void addGroups(Model model, List<Group> list) {
    model.addAttribute("groups", list);
  }

  /**
   * 设置站点列表
   * 
   * @param model
   * @param list
   */
  public static void addBookmarks(Model model, List<Bookmark> list) {
    model.addAttribute("bookmarks", list);
  }

  private ModelUtil() {

  }
}
