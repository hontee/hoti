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
import com.kuaiba.site.db.entity.Domain;
import com.kuaiba.site.db.entity.DomainExample;
import com.kuaiba.site.db.model.ResponseEntity;
import com.kuaiba.site.db.model.AjaxUtils;
import com.kuaiba.site.db.model.ComboBox;
import com.kuaiba.site.db.model.DataGrid;
import com.kuaiba.site.db.model.Pagination;
import com.kuaiba.site.front.co.BaseCO;
import com.kuaiba.site.front.vo.DomainVO;

@Controller
@RequestMapping(CmsURLs.CMS_DOMAINS)
public class DomainCMS extends BaseCO {

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/domains/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/domains/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/domains/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/domains/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DATALIST)
	public @ResponseBody List<ComboBox> datalist() throws Exception {
		DomainExample example = new DomainExample();
		example.createCriteria().andStateEqualTo((byte)1);
		example.setOrderByClause("weight DESC"); // 按权重排序
		List<Domain> list = domainService.findByExample(example);
		List<ComboBox> boxes = new ArrayList<>();
		list.forEach((org) -> boxes.add(new ComboBox(org.getId(), org.getTitle())));
		return boxes;
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.LIST)
	public @ResponseBody DataGrid<Domain> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		DomainExample example = new DomainExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Domain> pageInfo = domainService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.POST)
	@Log(action = "后台添加领域", table = TableIDs.DOMAIN, clazz = DomainVO.class)
	public @ResponseBody ResponseEntity add(DomainVO vo, HttpServletRequest request) {
		domainService.add(vo);
		return AjaxUtils.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DELETE, method = RequestMethod.POST)
	@Log(action = "后台删除领域", table = TableIDs.DOMAIN)
	public @ResponseBody ResponseEntity delete(@PathVariable Long id, HttpServletRequest request) {
		domainService.deleteByPrimaryKey(id);
		return AjaxUtils.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.POST)
	@Log(action = "后台编辑领域", table = TableIDs.DOMAIN, clazz = DomainVO.class)
	public @ResponseBody ResponseEntity edit(@PathVariable Long id, DomainVO vo, HttpServletRequest request) {
		domainService.updateByPrimaryKey(id, vo);
		return AjaxUtils.ok();
	}
	
	private Domain findByPrimaryKey(Long id) {
		return domainService.findByPrimaryKey(id);
	}
}
