package com.kuaiba.site.front.cms;

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
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.FollowUser;
import com.kuaiba.site.db.entity.FollowUserExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.StateUtil;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.db.entity.UserTypeUtil;
import com.kuaiba.site.front.controller.SiteUtil;
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.service.ActivityService;
import com.kuaiba.site.service.BookmarkService;
import com.kuaiba.site.service.Followable;

@Controller
@RequestMapping("/cms/bookmarks")
public class BookmarkCMS {

  private Logger logger = LoggerFactory.getLogger(BookmarkCMS.class);

  @Resource
  private BookmarkService bs;
  @Resource
  private Followable followable;
  @Resource
  private ActivityService as;


  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/bookmarks/index";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/bookmarks/new";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", bs.findOne(id));
    return "cms/bookmarks/edit";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/follow", method = RequestMethod.GET)
  public String followPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("id", id);
    return "cms/bookmarks/follow";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", bs.findOne(id));
    return "cms/bookmarks/view";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Bookmark> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Long category, @RequestParam(required = false) Byte state,
      Pagination p) throws Exception {

    BookmarkExample example = new BookmarkExample();
    BookmarkExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (category != null && category > 0) {
      criteria.andCategoryEqualTo(category);
    }

    if (StateUtil.validate(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Bookmark> pageInfo = bs.find(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/follows")
  public @ResponseBody DataGrid<FollowUser> followUsers(@PathVariable Long id,
      @RequestParam(required = false) String name, @RequestParam(required = false) Byte userType,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    FollowUserExample example = new FollowUserExample();
    FollowUserExample.Criteria criteria = example.createCriteria();

    criteria.andFidEqualTo(id); // FID必须

    if (StringUtils.isNotBlank(name)) {
      criteria.andNameLike("%" + name + "%"); // 模糊查询
    }

    if (UserTypeUtil.validate(userType)) {
      criteria.andUserTypeEqualTo(userType);
    }

    if (StateUtil.validate(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<FollowUser> pageInfo = followable.findBmfUser(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody SiteResponse add(BookmarkVO vo, HttpServletRequest request)
      throws SecurityException {
    as.addLogger("后台添加站点", TableIDs.BOOKMARK, JSON.toJSONString(vo), request);
    logger.info("后台添加站点: {}", JSON.toJSONString(vo));

    bs.add(vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    as.addLogger("后台删除站点", TableIDs.BOOKMARK, id.toString(), request);
    logger.info("后台删除站点: {}", id);

    bs.delete(id);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody SiteResponse edit(@PathVariable Long id, BookmarkVO vo,
      HttpServletRequest request) throws SecurityException {
    as.addLogger("后台编辑站点", TableIDs.BOOKMARK, id + ", " + JSON.toJSONString(vo), request);
    logger.info("后台编辑站点: {}, {}", id, JSON.toJSONString(vo));

    bs.update(id, vo);
    return SiteUtil.ok();
  }

}
