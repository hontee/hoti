package com.hoti.site.front.cms;

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
import com.hoti.site.db.entity.Bookmark;
import com.hoti.site.db.entity.BookmarkExample;
import com.hoti.site.db.entity.DataGrid;
import com.hoti.site.db.entity.FollowUser;
import com.hoti.site.db.entity.FollowUserExample;
import com.hoti.site.db.entity.GTypeUtil;
import com.hoti.site.db.entity.Group;
import com.hoti.site.db.entity.GroupExample;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.SiteResponse;
import com.hoti.site.db.entity.StateUtil;
import com.hoti.site.db.entity.TableIDs;
import com.hoti.site.db.entity.UserTypeUtil;
import com.hoti.site.front.controller.SiteUtil;
import com.hoti.site.front.vo.GroupVO;
import com.hoti.site.service.ActivityService;
import com.hoti.site.service.Countable;
import com.hoti.site.service.Followable;
import com.hoti.site.service.GroupService;

@Controller
@RequestMapping("/cms/groups")
public class GroupCMS {

  private Logger logger = LoggerFactory.getLogger(GroupCMS.class);

  @Resource
  private GroupService gs;
  @Resource
  private Followable followable;
  @Resource
  private Countable countable;
  @Resource
  private ActivityService as;

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/groups/index";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/groups/new";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", gs.findOne(id));
    return "cms/groups/edit";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/follow", method = RequestMethod.GET)
  public String followPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("id", id);
    return "cms/groups/follow";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/bookmark", method = RequestMethod.GET)
  public String bookmarkPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("id", id);
    return "cms/groups/bookmark";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/manager", method = RequestMethod.GET)
  public String managerPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("id", id);
    return "cms/groups/manager";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", gs.findOne(id));
    return "cms/groups/view";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Group> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Long category, @RequestParam(required = false) String gtype,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    GroupExample example = new GroupExample();
    GroupExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (category != null && category > 0) {
      criteria.andCategoryEqualTo(category);
    }

    if (StateUtil.validate(state)) {
      criteria.andStateEqualTo(state);
    }

    if (GTypeUtil.validate(gtype)) {
      criteria.andGtypeEqualTo(gtype);
    }

    PageInfo<Group> pageInfo = gs.find(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/bookmarks")
  public @ResponseBody DataGrid<Bookmark> groupBookmarks(@PathVariable Long id,
      @RequestParam(required = false) String title, @RequestParam(required = false) Long category,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    BookmarkExample example = new BookmarkExample();
    BookmarkExample.Criteria criteria = example.createCriteria();

    criteria.andGidEqualTo(id); // GID必须

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (category != null && category > 0) {
      criteria.andCategoryEqualTo(category);
    }

    if (StateUtil.validate(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Bookmark> pageInfo = followable.findGBRelation(example, p);
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

    PageInfo<FollowUser> pageInfo = followable.findGroupUser(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody SiteResponse add(GroupVO vo, HttpServletRequest request)
      throws SecurityException {
    as.addLogger("后台添加群组", TableIDs.GROUP, JSON.toJSONString(vo), request);
    logger.info("后台添加群组: {}", JSON.toJSONString(vo));
    
    gs.add(vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/bookmark", method = RequestMethod.POST)
  public @ResponseBody SiteResponse addBookmark(@RequestParam Long[] ids, @PathVariable Long id,
      HttpServletRequest request) throws SecurityException {
    as.addLogger("后台群组批量添加站点", TableIDs.GROUP_BOOKMARK, id + ", [" + ids + "]", request);
    logger.info("后台群组批量添加站点: {}, {}", id, ids);
    
    gs.add(id, ids);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/manager", method = RequestMethod.POST)
  public @ResponseBody SiteResponse removeBookmarks(@RequestParam Long[] ids, @PathVariable Long id,
      HttpServletRequest request) throws SecurityException {
    as.addLogger("后台群组批量移除站点", TableIDs.GROUP_BOOKMARK, id + ", [" + ids + "]", request);
    logger.info("后台群组批量移除站点: {}, {}", id, ids);

    gs.remove(id, ids);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    as.addLogger("后台删除群组", TableIDs.GROUP, id.toString(), request);
    logger.info("后台删除群组: {}", id);

    gs.delete(id);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody SiteResponse edit(@PathVariable Long id, GroupVO vo,
      HttpServletRequest request) throws SecurityException {
    as.addLogger("后台编辑群组", TableIDs.GROUP, id + ", " + JSON.toJSONString(vo), request);
    logger.info("后台编辑群组：{}, {}", id, JSON.toJSONString(vo));

    gs.update(id, vo);
    return SiteUtil.ok();
  }
  
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/pick", method = RequestMethod.POST)
  public @ResponseBody SiteResponse pick(@RequestParam Long[] ids,
      HttpServletRequest request) throws SecurityException {
    as.addLogger("后台群组精选", TableIDs.GROUP, ", [" + ids + "]", request);
    logger.info("后台群组精选: {}", ids);
    
    gs.pick(ids);
    return SiteUtil.ok();
  }
  
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/unpick", method = RequestMethod.POST)
  public @ResponseBody SiteResponse unpick(@RequestParam Long[] ids,
      HttpServletRequest request) throws SecurityException {
    as.addLogger("后台群组取消精选", TableIDs.GROUP, ", [" + ids + "]", request);
    logger.info("后台群组取消精选: {}", ids);
    
    gs.unpick(ids);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/count/task", method = RequestMethod.POST)
  public @ResponseBody SiteResponse countTask(HttpServletRequest request) throws SecurityException {
    as.addLogger("后台统计群组数据", TableIDs.GROUP, "系统任务", request);
    logger.info("后台统计群组数据");

    countable.countGroupTask();
    return SiteUtil.ok();
  }

}
