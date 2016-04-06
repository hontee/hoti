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
import com.kuaiba.site.db.entity.PagerUtil;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.service.BookmarkService;
import com.kuaiba.site.service.CategoryService;
import com.kuaiba.site.service.DomainService;
import com.kuaiba.site.service.GroupService;

@Controller
@Scope("prototype")
public class CategoryController {
	
	@Resource
	private BookmarkService bookmarkService;
	@Resource
	private GroupService groupService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private DomainService domainService;

	
	/**
	 * @WebPage 分类
	 * @param model
	 * @return
	 * @throws SecurityException
	 */
	@RequestMapping(value = "/cates", method = RequestMethod.GET)
	public String category(Model model) throws SecurityException {
		List<Domain> list = domainService.findAllWithCates(new DomainExample());
		model.addAttribute("records", list);
		return "views/category";
	}
	
	/**
	 * @WebPage 分类下的所有站点 （支持分页）
	 * @param id
	 * @param model
	 * @return
	 * @throws SecurityException
	 */
	@RequestMapping(value = "/cates/{id}", method = RequestMethod.GET)
	public String cateBm(
			@PathVariable Long id, 
			Model model, 
			@RequestParam(defaultValue = "site") String f, 
			Pagination p) throws SecurityException {
		
		p.initFrontRows();
		
		if ("site".equals(f)) { // 查询书签
			byBookmark(id, p, model);
		} else { // 查询主题
			byGroup(id, p, model);
		}
		
		return "views/cate-bm";
	}
	
	/**
	 * 书签
	 * @throws SecurityException 
	 */
	private void byBookmark(long id, Pagination p, Model model) throws SecurityException {
		Category record = categoryService.findOne(id);
		
		BookmarkExample example = new BookmarkExample();
		example.createCriteria().andCategoryEqualTo(id);
		PageInfo<Bookmark> pageInfo = bookmarkService.find(example, p);
		record.setBookmarks(pageInfo.getList());
		model.addAttribute("record", record);
		model.addAttribute("page", PagerUtil.of(pageInfo, "/cates/"+id +"?f=site"));
		model.addAttribute("f", "site");
		model.addAttribute("records", pageInfo.getList());
	}
	
	/**
	 * 主题
	 */
	private void byGroup(long id, Pagination p, Model model) throws SecurityException {
		Category record = categoryService.findOne(id);
		
		GroupExample example = new GroupExample();
		example.createCriteria().andCategoryEqualTo(id);
		PageInfo<Group> pageInfo = groupService.find(example, p);
		record.setGroups(pageInfo.getList());
		model.addAttribute("record", record);
		model.addAttribute("page", PagerUtil.of(pageInfo, "/cates/"+id +"?f=group"));
		model.addAttribute("f", "group");
		model.addAttribute("records", pageInfo.getList());
	}

}
