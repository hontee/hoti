package com.hoti.site.front.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Bookmark;
import com.hoti.site.db.entity.BookmarkExample;
import com.hoti.site.db.entity.Filter;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.service.BookmarkService;

@Controller
@Scope("prototype")
public class BookmarkController {

  @Resource
  private BookmarkService bs;

  /**
   * @WebPage 首页 = 我 | 猜你喜欢 | 全部 规则一：用户未登录则转发到 [发现页面] 规则二：用户已登录则查询 [我 / 全部]
   * @param p
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(@RequestParam(required = false) String f, Pagination p, Model model,
      HttpServletRequest request) throws SecurityException {

    p.initFrontRows();
    Filter filter = Filter.parse(f);
    f = filter.toString().toLowerCase();

    PageInfo<Bookmark> pageInfo = new PageInfo<>();
    BookmarkExample example = new BookmarkExample();
    BookmarkExample.Criteria criteria = example.createCriteria();

    if (filter == Filter.MY) { // 我的站点
      pageInfo = bs.find(example, p);
    } else if (filter == Filter.NEW) { // 最新
      p.setOrderBy("created", "DESC");
      pageInfo = bs.find(example, p);
    } else if (filter == Filter.HOT) { // 最热
      p.setOrderBy("star", "DESC");
      pageInfo = bs.find(example, p);
    } else if (filter == Filter.PICK) { // 精选
      criteria.andPickEqualTo((byte) 1);
      pageInfo = bs.find(example, p);
    }

    ModelUtil.addF(model, f);
    ModelUtil.addPager(model, pageInfo, "/?f=" + f);
    ModelUtil.addBookmarks(model, pageInfo.getList());
    ModelUtil.addHeader(model, "红提 | 为开发者而生");
    return "index.ftl";
  }

  /**
   * @WebAPI 更新站点点击率和获取响应URL
   * @param id
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/bookmarks/{id}/hit", method = RequestMethod.GET)
  public String hit(@PathVariable Long id, Model model) throws SecurityException {
    return SiteUtil.redirect(bs.updateHit(id));
  }

  /**
   * @WebPage 分享站点
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/share", method = RequestMethod.GET)
  public String share(Model model) throws SecurityException {
    ModelUtil.addHeader(model, "红提 | 推荐你喜欢的资源、文档、技术与开源项目");
    return "share.ftl";
  }

}
