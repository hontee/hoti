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
import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.StateUtil;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.front.controller.SiteUtil;
import com.kuaiba.site.front.vo.MtypeVO;
import com.kuaiba.site.service.ActivityService;
import com.kuaiba.site.service.MtypeService;

@Controller
@RequestMapping("/cms/mtypes")
public class MtypeCMS {

  private Logger logger = LoggerFactory.getLogger(MtypeCMS.class);

  @Resource
  private MtypeService mts;
  @Resource
  private ActivityService as;

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index() throws SecurityException {
    return "cms/mtypes/index";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String addPage() throws SecurityException {
    return "cms/mtypes/new";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public String editPage(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", mts.findOne(id));
    return "cms/mtypes/edit";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id, Model model) throws SecurityException {
    model.addAttribute("record", mts.findOne(id));
    return "cms/mtypes/view";
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/datalist")
  public @ResponseBody List<ComboBox> datalist() throws SecurityException {
    List<Mtype> list = mts.findAll();
    List<ComboBox> boxes = new ArrayList<>();
    list.forEach((m) -> boxes.add(new ComboBox(m.getId(), m.getTitle())));
    return boxes;
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/list")
  public @ResponseBody DataGrid<Mtype> dataGrid(@RequestParam(required = false) String title,
      @RequestParam(required = false) Byte state, Pagination p) throws SecurityException {

    MtypeExample example = new MtypeExample();
    MtypeExample.Criteria criteria = example.createCriteria();

    if (StringUtils.isNotBlank(title)) {
      criteria.andTitleLike("%" + title + "%"); // 模糊查询
    }

    if (StateUtil.validate(state)) {
      criteria.andStateEqualTo(state);
    }

    PageInfo<Mtype> pageInfo = mts.find(example, p);
    return new DataGrid<>(pageInfo);
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public @ResponseBody SiteResponse add(MtypeVO vo, HttpServletRequest request) throws SecurityException {
    as.addLogger("后台添加类型", TableIDs.MTYPE, JSON.toJSONString(vo), request);
    logger.info("后台添加类型: {}", JSON.toJSONString(vo));
    
    mts.add(vo);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request)
      throws SecurityException {
    as.addLogger("后台删除类型", TableIDs.MTYPE, id.toString(), request);
    logger.info("后台删除类型: {}", id);
    
    mts.delete(id);
    return SiteUtil.ok();
  }

  @RequiresRoles(value = "admin")
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public @ResponseBody SiteResponse edit(@PathVariable Long id, MtypeVO vo,
      HttpServletRequest request) throws SecurityException {
    as.addLogger("后台编辑类型", TableIDs.MTYPE, id + ", " + JSON.toJSONString(vo) , request);
    logger.info("后台编辑类型: {}, {}", id, JSON.toJSONString(vo));
    
    mts.update(id, vo);
    return SiteUtil.ok();
  }

}
