package com.kuaiba.site.front.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.Category;
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.service.BookmarkService;
import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.service.DomainService;
import com.kuaiba.site.service.GroupService;

@Controller
@Scope("prototype")
public class CategoryController {

  @Resource
  private BookmarkService bs;
  @Resource
  private GroupService gs;
  @Resource
  private CategoryService cs;
  @Resource
  private DomainService ds;
  
  @RequestMapping(value = "/category", method = RequestMethod.GET)
  public String  cate(Model model) throws SecurityException {
    DomainExample oe = new DomainExample();
    oe.createCriteria().andStateEqualTo((byte) 1);
    oe.setOrderByClause("weight DESC");
    List<Domain> domains = ds.findAllWithCates(oe);
    model.addAttribute("domains", domains);
    
    return "category.ftl";
  }

  /**
   * @WebPage 分类下的所有站点 （支持分页）
   * @param id
   * @param model
   * @return
   * @throws SecurityException
   */
  @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
  public String cateBm(@PathVariable Long id, Model model,
      @RequestParam(defaultValue = "site") String f, Pagination p) throws SecurityException {

    p.initFrontRows();
    ModelUtil.addHeader(model, "快吧分类");

    if ("site".equals(f)) { // 查询书签
      byBookmark(id, p, model);
    } else { // 查询主题
      byGroup(id, p, model);
    }

    return "category-list.ftl";
  }

  /**
   * 书签
   * 
   * @throws SecurityException
   */
  private void byBookmark(long id, Pagination p, Model model) throws SecurityException {
    Category record = cs.findOne(id);

    BookmarkExample example = new BookmarkExample();
    example.createCriteria().andCategoryEqualTo(id);
    PageInfo<Bookmark> pageInfo = bs.find(example, p);

    ModelUtil.addCategory(model, record);
    ModelUtil.addPager(model, pageInfo, "/category/" + id + "?f=site");
    ModelUtil.addBookmarks(model, pageInfo.getList());
    ModelUtil.addF(model, "site");
  }

  /**
   * 主题
   */
  private void byGroup(long id, Pagination p, Model model) throws SecurityException {
    Category record = cs.findOne(id);

    GroupExample example = new GroupExample();
    example.createCriteria().andCategoryEqualTo(id);
    PageInfo<Group> pageInfo = gs.find(example, p);

    ModelUtil.addCategory(model, record);
    ModelUtil.addPager(model, pageInfo, "/category/" + id + "?f=group");
    ModelUtil.addGroups(model, pageInfo.getList());
    ModelUtil.addF(model, "group");
  }

}
