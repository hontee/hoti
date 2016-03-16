package com.kuaiba.site.front.cms;

import java.util.ArrayList;
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
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.ComboBox;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.front.co.BaseCO;
import com.kuaiba.site.front.vo.MtypeVO;

@Controller
@RequestMapping(CmsURLs.CMS_MTYPES)
public class MtypeCSM extends BaseCO {
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/mtypes/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/mtypes/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/mtypes/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/mtypes/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DATALIST)
	public @ResponseBody List<ComboBox> datalist() throws Exception {
		MtypeExample example = new MtypeExample();
		example.createCriteria().andStateEqualTo((byte)1);
		example.setOrderByClause("weight DESC"); // 按权重排序
		List<Mtype> list = mtypeService.findByExample(example);
		List<ComboBox> boxes = new ArrayList<>();
		list.forEach((m) -> boxes.add(new ComboBox(m.getId(), m.getTitle())));
		return boxes;
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.LIST)
	public @ResponseBody DataGrid<Mtype> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		MtypeExample example = new MtypeExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Mtype> pageInfo = mtypeService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.POST)
	@Log(action = "后台添加类型", table = TableIDs.MTYPE, clazz = MtypeVO.class)
	public @ResponseBody SiteResponse add(MtypeVO vo) throws SecurityException {
		mtypeService.add(vo);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DELETE, method = RequestMethod.POST)
	@Log(action = "后台删除类型", table = TableIDs.MTYPE)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		mtypeService.deleteByPrimaryKey(id);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.POST)
	@Log(action = "后台编辑类型", table = TableIDs.MTYPE, clazz = MtypeVO.class)
	public @ResponseBody SiteResponse edit(@PathVariable Long id, MtypeVO vo, HttpServletRequest request) throws SecurityException {
		mtypeService.updateByPrimaryKey(id, vo);
		return ok();
	}
	
	private Mtype findByPrimaryKey(Long id) throws SecurityException {
		return mtypeService.findByPrimaryKey(id);
	}

}
