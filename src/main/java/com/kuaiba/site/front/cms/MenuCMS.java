package com.kuaiba.site.front.cms;

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
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.StateUtil;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.front.controller.SiteUtil;
import com.kuaiba.site.front.vo.MenuVO;
import com.kuaiba.site.service.ActivityService;
import com.kuaiba.site.service.MenuService;

@Controller
@RequestMapping("/cms/menus")
public class MenuCMS {

  private Logger logger = LoggerFactory.getLogger(MenuCMS.class);

  @Resource
  private MenuService ms;
  @Resource
  private ActivityService as;

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/menus/index";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/menus/new";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", ms.findOne(id));
    return "cms/menus/edit";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", ms.findOne(id));
    return "cms/menus/view";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/datalist")
  public @ResponseBody List<Menu> datalist() throws SecurityException {
    return ms.findAll();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Menu> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    MenuExample example = new MenuExample();
    MenuExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (StateUtil.validate(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Menu> pageInfo = ms.find(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody SiteResponse add(MenuVO vo, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台添加菜单: {}", JSON.toJSONString(vo));
    as.addLogger("后台添加菜单", TableIDs.MENU, JSON.toJSONString(vo), request);

    ms.add(vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台删除菜单: {}", id);
    as.addLogger("后台删除菜单", TableIDs.MENU, id.toString(), request);
    
    ms.delete(id);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody SiteResponse edit(@PathVariable Long id, MenuVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台编辑菜单: {}, {}", id, JSON.toJSONString(vo));
    as.addLogger("后台编辑菜单", TableIDs.MENU, id + ", " + JSON.toJSONString(vo), request);
    
    
    ms.update(id, vo);
    return SiteUtil.ok();
  }

}
