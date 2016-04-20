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
import com.hoti.site.db.entity.DataGrid;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.Recommend;
import com.hoti.site.db.entity.RecommendExample;
import com.hoti.site.db.entity.SiteResponse;
import com.hoti.site.db.entity.StateAuditUtil;
import com.hoti.site.front.controller.SiteUtil;
import com.hoti.site.front.vo.BookmarkVO;
import com.hoti.site.front.vo.RecommendVO;
import com.hoti.site.rest.BaseService;

@Controller
@RequestMapping("/cms/recommends")
public class RecommendCMS {

  private Logger logger = LoggerFactory.getLogger(RecommendCMS.class);

  @Resource
  private BaseService service;

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/recmds/index";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/recmds/new";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", service.findRecommend(id));
    return "cms/recmds/edit";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/ok", method = RequestMethod.GET)
  public String auditOKPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", service.findRecommend(id));
    return "cms/recmds/ok";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/refuse", method = RequestMethod.GET)
  public String auditRefusePage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", service.findRecommend(id));
    return "cms/recmds/refuse";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", service.findRecommend(id));
    return "cms/recmds/view";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Recommend> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    RecommendExample example = new RecommendExample();
    RecommendExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (StateAuditUtil.validate(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Recommend> pageInfo = service.findRecommends(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody SiteResponse add(@RequestParam String url, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台添加推荐: {}", url);
    service.addRecommend(url);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台删除推荐: {}", id);
    service.deleteRecommend(id);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody SiteResponse edit(@PathVariable Long id, RecommendVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台编辑推荐: {}, {}", id, JSON.toJSONString(vo));
    service.updateRecommend(id, vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/ok", method = RequestMethod.POST)
  public @ResponseBody SiteResponse auditOk(@PathVariable Long id, BookmarkVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台审核推荐通过: {}, {}", id, JSON.toJSONString(vo));
    service.auditRecommendOk(id, vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/refuse", method = RequestMethod.POST)
  public @ResponseBody SiteResponse auditRefuse(@PathVariable Long id, @RequestParam String remark,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台审核推荐拒绝: {}, {}", id, remark);
    service.auditRecommendRefuse(id, remark);
    return SiteUtil.ok();
  }

}
