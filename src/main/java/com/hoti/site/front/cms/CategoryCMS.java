package com.hoti.site.front.cms;

import java.util.ArrayList;
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
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.db.entity.Category;
import com.hoti.site.db.entity.CategoryExample;
import com.hoti.site.db.entity.ComboBox;
import com.hoti.site.db.entity.DataGrid;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.SiteResponse;
import com.hoti.site.db.entity.StateUtil;
import com.hoti.site.db.entity.TableIDs;
import com.hoti.site.front.controller.SiteUtil;
import com.hoti.site.front.vo.CategoryVO;
import com.hoti.site.service.ActivityService;
import com.hoti.site.service.CategoryService;
import com.hoti.site.service.Countable;

@Controller
@RequestMapping("/cms/cates")
public class CategoryCMS {

  private Logger logger = LoggerFactory.getLogger(CategoryCMS.class);

  @Resource
  private CategoryService cs;
  @Resource
  private Countable countable;
  @Resource
  private ActivityService as;

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/cates/index";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/cates/new";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", cs.findOne(id));
    return "cms/cates/edit";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", cs.findOne(id));
    return "cms/cates/view";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Category> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Long parent, @RequestParam(required = false) Byte state,
      Pagination p) throws SecurityException {

    CategoryExample example = new CategoryExample();
    CategoryExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (parent != null && parent > 0) {
      criteria.andParentEqualTo(parent);
    }

    if (StateUtil.validate(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Category> pageInfo = cs.find(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/datalist")
  public @ResponseBody List<ComboBox> datalist(@RequestParam(required = false) String q)
      throws SecurityException {
    List<Category> list = cs.findAll();
    List<ComboBox> boxes = new ArrayList<>();

    /**
     * 如果传入 ?q=all 则返回全部分类
     */
    if ("all".equals(q)) {
      boxes.add(new ComboBox(-1L, "全部分类"));
    }
    list.forEach((c) -> boxes.add(new ComboBox(c.getId(), c.getTitle())));
    return boxes;
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody SiteResponse add(CategoryVO vo, HttpServletRequest request)
      throws SecurityException {
    as.addLogger("后台添加分类", TableIDs.CATEGORY, JSON.toJSONString(vo), request);
    logger.info("后台添加分类: {}", JSON.toJSONString(vo));
    
    cs.add(vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    as.addLogger("后台删除分类", TableIDs.CATEGORY, id.toString(), request);
    logger.info("后台删除分类: {}", id);
    
    cs.delete(id);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody SiteResponse edit(@PathVariable Long id, CategoryVO vo,
      HttpServletRequest request) throws SecurityException {
    as.addLogger("后台编辑分类", TableIDs.CATEGORY, id + ", " + JSON.toJSONString(vo), request);
    logger.info("后台编辑分类：{}, {}", id, JSON.toJSONString(vo));
    
    cs.update(id, vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/count/task", method = RequestMethod.POST)
  public @ResponseBody SiteResponse countTask(HttpServletRequest request) throws SecurityException {
    as.addLogger("后台统计分类数据", TableIDs.CATEGORY, "系统任务", request);
    logger.info("后台统计分类数据");
    
    countable.countCategoryTask();
    return SiteUtil.ok();
  }

}
