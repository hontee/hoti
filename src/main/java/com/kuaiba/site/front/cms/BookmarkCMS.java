package com.kuaiba.site.front.cms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Bookmark;
import com.kuaiba.site.db.entity.BookmarkExample;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.front.controller.BaseController;
import com.kuaiba.site.front.vo.BookmarkVO;
import com.kuaiba.site.interceptor.SiteLog;

@Controller
@RequestMapping(CmsIDs.CMS_BOOKMARKS)
public class BookmarkCMS extends BaseController {

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/bookmarks/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/bookmarks/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/bookmarks/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/bookmarks/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.LIST)
	public @ResponseBody DataGrid<Bookmark> dataGrid(
			@RequestParam(required = false) String title, 
			@RequestParam(required = false) Long category, 
			@RequestParam(required = false) Byte state, 
			Pagination p) throws Exception {
		
		BookmarkExample example = new BookmarkExample();
		BookmarkExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(title)) {
			criteria.andTitleLike("%" + title + "%"); // 模糊查询
		}
		
		if (category != null && category > 0) {
			criteria.andCategoryEqualTo(category);
		}
		
		if (Bookmark.checkState(state)) {
			criteria.andStateEqualTo(state);
		}
		
		PageInfo<Bookmark> pageInfo = bookmarkService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.CREATE, method = RequestMethod.POST)
	@SiteLog(action = "后台添加站点", table = TableIDs.BOOKMARK, clazz = BookmarkVO.class)
	public @ResponseBody SiteResponse add(BookmarkVO vo, HttpServletRequest request) throws SecurityException {
		bookmarkService.add(vo);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.DELETE, method = RequestMethod.POST)
	@SiteLog(action = "后台删除站点", table = TableIDs.BOOKMARK)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		bookmarkService.deleteByPrimaryKey(id);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsIDs.EDIT, method = RequestMethod.POST)
	@SiteLog(action = "后台编辑站点", table = TableIDs.BOOKMARK, clazz = BookmarkVO.class)
	public @ResponseBody SiteResponse edit(@PathVariable Long id, BookmarkVO vo, HttpServletRequest request) throws SecurityException {
		bookmarkService.updateByPrimaryKey(id, vo);
		return ok();
	}
	
	private Bookmark findByPrimaryKey(Long id) throws SecurityException {
		return bookmarkService.findByPrimaryKey(id);
	}

}
