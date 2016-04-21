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
import com.hoti.site.db.entity.VUtil;
import com.hoti.site.front.controller.BaseController;
import com.hoti.site.front.controller.ModelUtil;
import com.hoti.site.front.vo.ProductVO;
import com.hoti.site.front.vo.RecommendVO;
import com.hoti.site.front.vo.ResponseVO;
import com.hoti.site.rest.BaseService;

@Controller
@RequestMapping("/cms/recommends")
public class RecommendCMS extends BaseController {

  private Logger logger = LoggerFactory.getLogger(RecommendCMS.class);

  @Resource
  private BaseService service;

  /**
   * 推荐管理首页
   * 
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/recmds/index";
  }

  /**
   * 新建推荐页
   * 
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/recmds/new";
  }

  /**
   * 编辑推荐页
   * 
   * @param id 推荐ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    ModelUtil.addRecord(model, service.findRecommend(id));
    return "cms/recmds/edit";
  }

  /**
   * 审核通过页
   * 
   * @param id 推荐ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/ok", method = RequestMethod.GET)
  public String auditOKPage(@PathVariable Long id, Model model) throws SecurityException {
    ModelUtil.addRecord(model, service.findRecommend(id));
    return "cms/recmds/ok";
  }

  /**
   * 审核拒绝页
   * 
   * @param id 推荐ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/refuse", method = RequestMethod.GET)
  public String auditRefusePage(@PathVariable Long id, Model model) throws SecurityException {
    ModelUtil.addRecord(model, service.findRecommend(id));
    return "cms/recmds/refuse";
  }

  /**
   * 推荐详情页
   * 
   * @param id 推荐ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    ModelUtil.addRecord(model, service.findRecommend(id));
    return "cms/recmds/view";
  }

  /**
   * 推荐数据列表, 支持分页和查询条件
   * 
   * @param title 标题
   * @param state 状态 1=未审核 2=审核通过 3=审核拒绝
   * @param p 分页组件
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Recommend> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    RecommendExample example = new RecommendExample();
    RecommendExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (VUtil.assertAudit(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Recommend> pageInfo = service.findRecommends(example, p);
    return new DataGrid<>(pageInfo);
  }

  /**
   * 新建推荐
   * 
   * @param url 推荐网址
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody ResponseVO addRecommend(@RequestParam String url, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台添加推荐: {}", url);
    service.addRecommend(url);
    return buildResponse();
  }

  /**
   * 删除推荐
   * 
   * @param id 推荐ID
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody ResponseVO deleteRecommend(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    logger.info("后台删除推荐: {}", id);
    service.deleteRecommend(id);
    return buildResponse();
  }

  /**
   * 编辑推荐
   * 
   * @param id 推荐ID
   * @param vo 请求参数
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody ResponseVO editRecommend(@PathVariable Long id, RecommendVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台编辑推荐: {}, {}", id, JSON.toJSONString(vo));
    service.updateRecommend(id, vo);
    return buildResponse();
  }

  /**
   * 审核通过
   * 
   * @param id 推荐ID
   * @param vo 请求参数
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/ok", method = RequestMethod.POST)
  public @ResponseBody ResponseVO auditRecommendOk(@PathVariable Long id, ProductVO vo,
      HttpServletRequest request) throws SecurityException {
    logger.info("后台审核推荐通过: {}, {}", id, JSON.toJSONString(vo));
    service.auditRecommendOk(id, vo);
    return buildResponse();
  }

  /**
   * 审核拒绝
   * 
   * @param id 推荐ID
   * @param remark 拒绝理由
   * @param request
   * @return
   * @throws SecurityException
   */
  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/refuse", method = RequestMethod.POST)
  public @ResponseBody ResponseVO auditRecommendRefuse(@PathVariable Long id,
      @RequestParam String remark, HttpServletRequest request) throws SecurityException {
    logger.info("后台审核推荐拒绝: {}, {}", id, remark);
    service.auditRecommendRefuse(id, remark);
    return buildResponse();
  }

}
