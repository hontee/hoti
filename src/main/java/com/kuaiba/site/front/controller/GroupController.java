package com.kuaiba.site.front.controller;

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
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.Filter;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.service.GroupService;

@Controller
@Scope("prototype")
public class GroupController {

  @Resource
  private GroupService gs;

  /**
   * @WebPage 群组 = 我 | 猜你喜欢 | 全部
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/groups", method = RequestMethod.GET)
  public String group(@RequestParam(required = false) String f, Pagination p, Model model,
      HttpServletRequest request) throws SecurityException {

    p.initFrontRows();
    Filter filter = Filter.parse(f);
    f = filter.toString().toLowerCase();
    PageInfo<Group> pageInfo = new PageInfo<>();
    GroupExample example = new GroupExample();
    GroupExample.Criteria criteria = example.createCriteria();

    if (filter == Filter.MY) { // 我的站点
      pageInfo = gs.find(example, p);
    } else if (filter == Filter.NEW) { // 最新
      p.setOrderBy("created", "DESC");
      pageInfo = gs.find(example, p);
    } else if (filter == Filter.HOT) { // 最热
      p.setOrderBy("star", "DESC");
      pageInfo = gs.find(example, p);
    } else if (filter == Filter.PICK) { // 精选
      criteria.andPickEqualTo((byte) 1);
      pageInfo = gs.find(example, p);
    }

    ModelUtil.addHeader(model, "快吧主题");
    ModelUtil.addF(model, f);
    ModelUtil.addPager(model, pageInfo, "/groups?f=" + f);
    ModelUtil.addGroups(model, pageInfo.getList());
    return "group.ftl";
  }

  /**
   * @WebPage 群组下的所有站点（支持分页）
   * @param id
   * @param model
   * @param p
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
  public String group(@PathVariable Long id, @RequestParam(defaultValue = "hot") String f,
      Model model, Pagination p, HttpServletRequest request) throws SecurityException {

    p.initFrontRows();
    Filter filter = Filter.parse(f);
    f = filter.toString().toLowerCase();
    Group record = gs.findOne(id);

    BookmarkExample example = new BookmarkExample();
    BookmarkExample.Criteria criteria = example.createCriteria();
    criteria.andGidEqualTo(id);

    if (filter == Filter.HOT) {
      p.setOrderBy("star", "DESC");
    } else if (filter == Filter.NEW) {
      p.setOrderBy("created", "DESC");
    } else if (filter == Filter.PICK) {
      criteria.andPickEqualTo((byte) 1);
    }

    PageInfo<Bookmark> pageInfo = gs.find(example, p);
    ModelUtil.addHeader(model, "快吧主题");
    ModelUtil.addF(model, f);
    ModelUtil.addPager(model, pageInfo, "/groups/" + id + "/?f=" + f);
    ModelUtil.addGroup(model, record);
    ModelUtil.addBookmarks(model, pageInfo.getList());
    return "group-bm.ftl";
  }

}
