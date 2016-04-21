package com.hoti.site.front.controller;

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
import com.hoti.site.core.exception.SecurityException;
import com.hoti.site.core.security.AuthzUtil;
import com.hoti.site.db.entity.Filter;
import com.hoti.site.db.entity.Pagination;
import com.hoti.site.db.entity.Product;
import com.hoti.site.db.entity.ProductExample;
import com.hoti.site.rest.BaseService;

@Controller
@Scope("prototype")
public class ProductController extends BaseController {

  @Resource
  private BaseService service;

  /**
   * 首页 = 我的关注 | 精选 | 最热 | 最新 <br>
   * 如果用户未登录，则默认为[精选]
   * 
   * @param p 分页组件
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(@RequestParam(required = false) String f, Pagination p, Model model,
      HttpServletRequest request) throws SecurityException {

    p.initFrontRows();
    Filter filter = Filter.parse(f);
    f = filter.toString().toLowerCase();

    PageInfo<Product> pageInfo = new PageInfo<>();
    ProductExample example = new ProductExample();
    ProductExample.Criteria criteria = example.createCriteria();

    if (filter == Filter.MY) { // 我的产品
      pageInfo = service.findUserProducts(AuthzUtil.getUserId(), p);
    } else if (filter == Filter.NEW) { // 最新
      p.setOrderBy("created", "DESC");
      pageInfo = service.findProducts(example, p);
    } else if (filter == Filter.HOT) { // 最热
      p.setOrderBy("star", "DESC");
      pageInfo = service.findProducts(example, p);
    } else if (filter == Filter.PICK) { // 精选
      criteria.andPickEqualTo((byte) 1);
      pageInfo = service.findProducts(example, p);
    }

    ModelUtil.addF(model, f);
    ModelUtil.addPager(model, pageInfo, "/?f=" + f);
    ModelUtil.addProducts(model, pageInfo.getList());
    ModelUtil.addHeader(model, "红提 | 为开发者而生");
    return "index.ftl";
  }

  /**
   * 更新产品点击率和获取响应URL
   * @param id 产品ID
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/products/{id}/hit", method = RequestMethod.GET)
  public String productHit(@PathVariable Long id, Model model) throws SecurityException {
    return redirect(service.updateProductHit(id));
  }

  /**
   * 推荐产品
   * 
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/recommend", method = RequestMethod.GET)
  public String recommend(Model model) throws SecurityException {
    ModelUtil.addHeader(model, "红提 | 推荐你喜欢的资源、文档、技术与开源项目");
    return "recommend.ftl";
  }

}
