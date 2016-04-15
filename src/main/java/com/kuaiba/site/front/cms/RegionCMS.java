package com.kuaiba.site.front.cms;

import java.util.Arrays;
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
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.Region;
import com.kuaiba.site.db.entity.RegionExample;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.StateUtil;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.front.controller.SiteUtil;
import com.kuaiba.site.service.ActivityService;
import com.kuaiba.site.service.RegionService;

@Controller
@RequestMapping("/cms/regions")
public class RegionCMS {

  private Logger logger = LoggerFactory.getLogger(RegionCMS.class);

  @Resource
  private RegionService rs;
  @Resource
  private ActivityService as;

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/regions/index";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/regions/new";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Integer id, Model model) throws SecurityException {
    model.addAttribute("record", rs.findOne(id));
    return "cms/regions/edit";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Integer id, Model model) throws SecurityException {
    model.addAttribute("record", rs.findOne(id));
    return "cms/regions/view";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/datalist")
  public @ResponseBody List<Region> datalist() throws SecurityException {
    RegionExample example = new RegionExample();
    RegionExample.Criteria criteria = example.createCriteria();
    criteria.andParentEqualTo(1);
    return rs.findAll(example);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Region> dataGrid(@RequestParam(required = false) String name,
      @RequestParam(required = false) Byte state,
      @RequestParam(required = false) Byte level,
      Pagination p) throws SecurityException {

    RegionExample example = new RegionExample();
    RegionExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(name)) {
      criteria.andNameLike("%" + name + "%"); // 模糊查询
    }

    if (StateUtil.validate(state)) {
      criteria.andStateEqualTo(state);
    }
    
    final Byte[] levels = {1, 2, 3};
    
    if (Arrays.asList(levels).contains(level)) {
      criteria.andLevelEqualTo(level);
    }

    PageInfo<Region> pageInfo = rs.find(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody SiteResponse add(Region vo, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台添加城市: {}", JSON.toJSONString(vo));
    as.addLogger("后台添加城市", TableIDs.REGION, JSON.toJSONString(vo), request);

    rs.add(vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody SiteResponse delete(@PathVariable Integer id, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台删除城市: {}", id);
    as.addLogger("后台删除城市", TableIDs.REGION, id.toString(), request);
    
    rs.delete(id);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody SiteResponse edit(@PathVariable Integer id, Region vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台编辑城市: {}, {}", id, JSON.toJSONString(vo));
    as.addLogger("后台编辑城市", TableIDs.REGION, id + ", " + JSON.toJSONString(vo), request);
    
    vo.setId(id);
    rs.update(vo);
    return SiteUtil.ok();
  }

}
