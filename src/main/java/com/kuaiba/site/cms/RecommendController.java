package com.kuaiba.site.cms;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.db.entity.RecommendExample;
import com.kuaiba.site.net.FetchUtils;
import com.kuaiba.site.net.FetchUtils.WebModel;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.support.DataGrid;
import com.kuaiba.site.support.Pagination;
import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;

@Controller
@RequestMapping("/cms/recmds")
public class RecommendController {
	
	@Resource
	private RecommendService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "cms/recmds/index";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String addPage() {
		return "cms/recmds/new";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editPage(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("record", findByPrimaryKey(id));
		} catch (Exception e) {
		}
		return "cms/recmds/edit";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("record", findByPrimaryKey(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "cms/recmds/view";
	}
	
	@RequestMapping(value = "/list")
	public @ResponseBody DataGrid<Recommend> dataGrid(@RequestParam(required = false) String title, Pagination p) throws Exception {
		RecommendExample example = new RecommendExample();
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andTitleLike("%" + title + "%"); // 模糊查询
		}
		PageInfo<Recommend> pageInfo = service.findByExample(example, p);
		return new DataGrid<>(pageInfo);
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public @ResponseBody Result add(@RequestParam String url) {
		WebModel wm = FetchUtils.connect(url);
		Recommend record = new Recommend();
		record.setCreator("admin");
		record.setDescription(wm.getDescription());
		record.setName(UUID.randomUUID().toString());
		record.setState((byte)1); // 待审核
		record.setTitle(wm.getTitle());
		record.setUrl(url);
		record.setKeywords(wm.getKeywords());
		service.add(record);
		return ResultBuilder.ok();
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(@PathVariable Long id) {
		try {
			service.deleteByPrimaryKey(id);
			return ResultBuilder.ok();
		} catch (Exception e) {
			return ResultBuilder.failed(e);
		}
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public @ResponseBody Result edit(@PathVariable Long id, 
			@RequestParam String title, 
			@RequestParam String keywords,
			@RequestParam(defaultValue = "无") String description, 
			@RequestParam(defaultValue = "1") Byte state) {
		Recommend record = new Recommend();
		record.setId(id);
		record.setDescription(description);
		record.setState(state); // 待审核
		record.setTitle(title);
		record.setKeywords(keywords);
		service.updateByPrimaryKey(record);
		return ResultBuilder.ok();
	}
	
	private Recommend findByPrimaryKey(Long id) {
		return service.findByPrimaryKey(id);
	}

}
