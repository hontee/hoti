package com.kuaiba.site.front.cms;

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
import com.kuaiba.site.core.CmsURLs;
import com.kuaiba.site.db.entity.Group;
import com.kuaiba.site.db.entity.GroupExample;
import com.kuaiba.site.front.co.BaseCO;
import com.kuaiba.site.front.vo.GroupVO;
import com.kuaiba.site.service.utils.AjaxResponse;
import com.kuaiba.site.service.utils.AjaxUtils;
import com.kuaiba.site.service.utils.DataGrid;
import com.kuaiba.site.service.utils.Pagination;

@Controller
@RequestMapping(CmsURLs.CMS_GROUPS)
public class GroupCMS extends BaseCO {

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/groups/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.GET)
	public String addPage() {
		return "cms/groups/new";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/groups/edit";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/groups/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.LIST)
	public @ResponseBody DataGrid<Group> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		GroupExample example = new GroupExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Group> pageInfo = groupService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.CREATE, method = RequestMethod.POST)
	public @ResponseBody AjaxResponse add(GroupVO vo) {
		groupService.add(vo);
		return AjaxUtils.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DELETE, method = RequestMethod.POST)
	public @ResponseBody AjaxResponse delete(@PathVariable Long id) {
		groupService.deleteByPrimaryKey(id);
		return AjaxUtils.ok();
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.EDIT, method = RequestMethod.POST)
	public @ResponseBody AjaxResponse edit(@PathVariable Long id, GroupVO vo) {
		groupService.updateByPrimaryKey(id, vo);
		return AjaxUtils.ok();
	}
	
	private Group findByPrimaryKey(Long id) {
		return groupService.findByPrimaryKey(id);
	}
	
}
