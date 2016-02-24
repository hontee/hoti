package com.kuaiba.site.views;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuaiba.site.db.entity.Recommend;
import com.kuaiba.site.net.FetchUtils;
import com.kuaiba.site.net.FetchUtils.WebModel;
import com.kuaiba.site.service.RecommendService;
import com.kuaiba.site.service.WebsiteService;
import com.kuaiba.site.support.Result;
import com.kuaiba.site.support.ResultBuilder;

/**
 * 站点
 * @author larry.qi
 *
 */
@Controller
public class WebsiteAction {
	
	@Resource
	private RecommendService recommend;
	
	@Resource
	private WebsiteService service;
	
	/**
	 * 分享站点
	 * @return
	 */
	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public String sharePage() {
		return "views/website-share";
	}
	
	/**
	 * 分享站点
	 * @return
	 */
	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public String share(@RequestParam String url, Model model) {
		WebModel wm = FetchUtils.connect(url);
		Recommend record = new Recommend();
		record.setCreator("admin");
		record.setDescription(wm.getDescription());
		record.setName(UUID.randomUUID().toString());
		record.setState((byte)1); // 待审核
		record.setTitle(wm.getTitle());
		record.setUrl(url);
		record.setKeywords(wm.getKeywords());
		recommend.add(record);
		model.addAttribute("wm", wm);
		model.addAttribute("result", ResultBuilder.ok());
		return "views/website-share";
	}
	
	/**
	 * 添加关注
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/website/{id}/follow", method = RequestMethod.POST)
	public Result follow(@PathVariable Long id) {
		service.follow(1L, id);
		return ResultBuilder.ok();
	}
	
	/**
	 * 取消关注
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/website/{id}/unfollow", method = RequestMethod.POST)
	public Result unfollow(@PathVariable Long id) {
		service.unfollow(1L, id);
		return ResultBuilder.ok();
	}
	
}
