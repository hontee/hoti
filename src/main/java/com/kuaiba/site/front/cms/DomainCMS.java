package com.kuaiba.site.front.cms;

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
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.ComboBox;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.StateUtil;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.front.controller.SiteUtil;
import com.kuaiba.site.front.vo.DomainVO;
import com.kuaiba.site.service.ActivityService;
import com.kuaiba.site.service.Countable;
import com.kuaiba.site.service.DomainService;

@Controller
@RequestMapping("/cms/domains")
public class DomainCMS {

  private Logger logger = LoggerFactory.getLogger(DomainCMS.class);

  @Resource
  private DomainService ds;
  @Resource
  private Countable countable;
  @Resource
  private ActivityService as;

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/domains/index";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/domains/new";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", ds.findOne(id));
    return "cms/domains/edit";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", ds.findOne(id));
    return "cms/domains/view";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/datalist")
  public @ResponseBody List<ComboBox> datalist(@RequestParam(required = false) String q)
      throws SecurityException {
    List<Domain> list = ds.findAll();
    List<ComboBox> boxes = new ArrayList<>();

    if ("all".equals(q)) {
      boxes.add(new ComboBox(-1L, "全部领域"));
    }

    list.forEach((org) -> boxes.add(new ComboBox(org.getId(), org.getTitle())));
    return boxes;
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Domain> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    DomainExample example = new DomainExample();
    DomainExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (StateUtil.validate(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Domain> pageInfo = ds.find(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody SiteResponse add(DomainVO vo, HttpServletRequest request)
      throws SecurityException {
    as.addLogger("后台添加领域", TableIDs.DOMAIN, JSON.toJSONString(vo), request);
    logger.info("后台添加领域: {}", JSON.toJSONString(vo));

    ds.add(vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    as.addLogger("后台删除领域", TableIDs.DOMAIN, id.toString(), request);
    logger.info("后台删除领域：{}", id);

    ds.delete(id);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody SiteResponse edit(@PathVariable Long id, DomainVO vo,
      HttpServletRequest request) throws SecurityException {
    as.addLogger("后台编辑领域", TableIDs.DOMAIN, id + ", " + JSON.toJSONString(vo), request);
    logger.info("后台编辑领域：{}, {}", id, JSON.toJSONString(vo));

    ds.update(id, vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/count/task", method = RequestMethod.POST)
  public @ResponseBody SiteResponse countTask(HttpServletRequest request) throws SecurityException {
    as.addLogger("后台统计领域数据", TableIDs.DOMAIN, "系统任务", request);
    logger.info("后台统计领域数据");

    countable.countDomainTask();
    return SiteUtil.ok();
  }

}
