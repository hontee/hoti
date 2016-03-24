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
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.ComboBox;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.Mtype;
import com.kuaiba.site.db.entity.MtypeExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.front.controller.BaseController;
import com.kuaiba.site.front.vo.MtypeVO;
import com.kuaiba.site.interceptor.SiteLog;

@Controller
@RequestMapping("/cms/mtypes")
public class MtypeCSM extends BaseController {
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "cms/mtypes/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addPage() {
		return "cms/mtypes/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", mtypeService.read(id));
		return "cms/mtypes/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", mtypeService.read(id));
		return "cms/mtypes/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/datalist")
	public @ResponseBody List<ComboBox> datalist() throws Exception {
		MtypeExample example = new MtypeExample();
		example.createCriteria().andStateEqualTo((byte)1);
		example.setOrderByClause("weight DESC"); // 按权重排序
		List<Mtype> list = mtypeService.read(example);
		List<ComboBox> boxes = new ArrayList<>();
		list.forEach((m) -> boxes.add(new ComboBox(m.getId(), m.getTitle())));
		return boxes;
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Mtype> dataGrid(
			@RequestParam(required = false) String title, 
			@RequestParam(required = false) Byte state,
			Pagination p) throws Exception {
		
		MtypeExample example = new MtypeExample();
		MtypeExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(title)) {
			criteria.andTitleLike("%" + title + "%"); // 模糊查询
		}
		
		if (Mtype.checkState(state)) {
			criteria.andStateEqualTo(state);
		}
		
		PageInfo<Mtype> pageInfo = mtypeService.search(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@SiteLog(action = "后台添加类型", table = TableIDs.MTYPE, clazz = MtypeVO.class)
	public @ResponseBody SiteResponse add(MtypeVO vo) throws SecurityException {
		mtypeService.add(vo);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@SiteLog(action = "后台删除类型", table = TableIDs.MTYPE)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		mtypeService.delete(id);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@SiteLog(action = "后台编辑类型", table = TableIDs.MTYPE, clazz = MtypeVO.class)
	public @ResponseBody SiteResponse edit(@PathVariable Long id, MtypeVO vo, HttpServletRequest request) throws SecurityException {
		mtypeService.update(id, vo);
		return ok();
	}

}
