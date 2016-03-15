package com.kuaiba.site.front.cms;

import java.util.List;

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
import com.kuaiba.site.aop.Log;
import com.kuaiba.site.core.CmsURLs;
import com.kuaiba.site.core.TableIDs;
import com.kuaiba.site.db.entity.Menu;
import com.kuaiba.site.db.entity.MenuExample;
import com.kuaiba.site.db.model.ResponseEntity;
import com.kuaiba.site.db.model.AjaxUtils;
import com.kuaiba.site.db.model.DataGrid;
import com.kuaiba.site.db.model.Pagination;
import com.kuaiba.site.front.co.BaseCO;
import com.kuaiba.site.front.vo.MenuVO;

@Controller
@RequestMapping(CmsURLs.CMS_MENUS)
public class MenuCMS extends BaseCO {
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/menus/index";
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/menus/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/menus/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/menus/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DATALIST)
	public @ResponseBody List<Menu> datalist() throws Exception {
		MenuExample example = new MenuExample();
		example.createCriteria().andStateEqualTo((byte)1);
		example.setOrderByClause("weight DESC"); // 按权重排序
		return menuService.findByExample(example);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.LIST)
	public @ResponseBody DataGrid<Menu> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		MenuExample example = new MenuExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Menu> pageInfo = menuService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.POST)
	@Log(action = "后台添加菜单", table = TableIDs.MENU, clazz = MenuVO.class)
	public @ResponseBody ResponseEntity add(MenuVO vo, HttpServletRequest request) {
		menuService.add(vo);
		return AjaxUtils.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DELETE, method = RequestMethod.POST)
	@Log(action = "后台删除菜单", table = TableIDs.MENU)
	public @ResponseBody ResponseEntity delete(@PathVariable Long id, HttpServletRequest request) {
		menuService.deleteByPrimaryKey(id);
		return AjaxUtils.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.POST)
	@Log(action = "后台编辑菜单", table = TableIDs.MENU, clazz = MenuVO.class)
	public @ResponseBody ResponseEntity edit(@PathVariable Long id, MenuVO vo, HttpServletRequest request) {
		menuService.updateByPrimaryKey(id, vo);
		return AjaxUtils.ok();
	}
	
	private Menu findByPrimaryKey(Long id) {
		return menuService.findByPrimaryKey(id);
	}

}
