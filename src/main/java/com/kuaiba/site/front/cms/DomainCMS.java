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
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.SiteResponse;
import com.kuaiba.site.db.entity.TableIDs;
import com.kuaiba.site.front.controller.BaseController;
import com.kuaiba.site.front.vo.DomainVO;
import com.kuaiba.site.interceptor.SiteLog;

@Controller
@RequestMapping("/cms/domains")
public class DomainCMS extends BaseController {

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "cms/domains/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addPage() {
		return "cms/domains/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", domainService.read(id));
		return "cms/domains/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) throws SecurityException {
		model.addAttribute("record", domainService.read(id));
		return "cms/domains/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/datalist")
	public @ResponseBody List<ComboBox> datalist(
			@RequestParam(required=false) String q) throws Exception {
		DomainExample example = new DomainExample();
		example.createCriteria().andStateEqualTo((byte)1);
		example.setOrderByClause("weight DESC"); // 按权重排序
		List<Domain> list = domainService.read(example);
		List<ComboBox> boxes = new ArrayList<>();
		
		if ("all".equals(q)) {
			boxes.add(new ComboBox(-1L, "全部领域"));
		}
		
		list.forEach((org) -> boxes.add(new ComboBox(org.getId(), org.getTitle())));
		return boxes;
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Domain> dataGrid(
			@RequestParam(required = false) String title, 
			@RequestParam(required = false) Byte state, 
			Pagination p) throws Exception {
		
		DomainExample example = new DomainExample();
		DomainExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(title)) {
			criteria.andTitleLike("%" + title + "%"); // 模糊查询
		}
		
		if (Domain.checkState(state)) {
			criteria.andStateEqualTo(state);
		}
		
		PageInfo<Domain> pageInfo = domainService.search(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@SiteLog(action = "后台添加领域", table = TableIDs.DOMAIN, clazz = DomainVO.class)
	public @ResponseBody SiteResponse add(DomainVO vo, HttpServletRequest request) throws SecurityException {
		domainService.add(vo);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@SiteLog(action = "后台删除领域", table = TableIDs.DOMAIN)
	public @ResponseBody SiteResponse delete(@PathVariable Long id, HttpServletRequest request) throws SecurityException {
		domainService.delete(id);
		return ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@SiteLog(action = "后台编辑领域", table = TableIDs.DOMAIN, clazz = DomainVO.class)
	public @ResponseBody SiteResponse edit(@PathVariable Long id, DomainVO vo, HttpServletRequest request) throws SecurityException {
		domainService.update(id, vo);
		return ok();
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "/count/task", method = RequestMethod.POST)
	@SiteLog(action = "后台统计领域数据", table = TableIDs.DOMAIN, clazz = String.class)
	public @ResponseBody SiteResponse countTask(
			@RequestParam(defaultValue = "后台统计领域数据") String desc, 
			HttpServletRequest request) throws SecurityException {
		countable.countDomainTask();
		return ok();
	}
	
}
