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
import com.kuaiba.site.aop.Log;
import com.kuaiba.site.core.CmsURLs;
import com.kuaiba.site.core.TableIDs;
import com.kuaiba.site.db.entity.Activity;
import com.kuaiba.site.db.entity.ActivityExample;
import com.kuaiba.site.db.entity.DataGrid;
import com.kuaiba.site.db.entity.Pagination;
import com.kuaiba.site.db.entity.Response;
import com.kuaiba.site.front.co.BaseCO;

@Controller
@RequestMapping(CmsURLs.CMS_ACTIVITIES)
public class ActivityCMS extends BaseCO {
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.HOME, method = RequestMethod.GET)
	public String index() {
		return "cms/activities/index";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.VIEW, method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("record", findByPrimaryKey(id));
		return "cms/activities/view";
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.LIST)
	public @ResponseBody DataGrid<Activity> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		ActivityExample example = new ActivityExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andNameLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Activity> pageInfo = activityService.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}

	@RequiresRoles(value = "admin")
	@RequestMapping(value = CmsURLs.DELETE, method = RequestMethod.POST)
	@Log(action = "后台删除操作记录", table = TableIDs.ACTIVITY)
	public @ResponseBody Response delete(@PathVariable Long id, HttpServletRequest request) {
		activityService.deleteByPrimaryKey(id);
		return ok();
	}
	
	private Activity findByPrimaryKey(Long id) {
		return activityService.findByPrimaryKey(id);
	}

}
