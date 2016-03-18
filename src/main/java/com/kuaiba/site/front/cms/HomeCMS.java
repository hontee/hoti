package com.kuaiba.site.front.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kuaiba.site.front.controller.BaseController;

@Controller
@RequestMapping(CmsIDs.CMS_HOME)
public class HomeCMS extends BaseController {
	
	@RequestMapping(value = CmsIDs.HOME, method = RequestMethod.GET)
	public String home() {
		return "cms/home";
	}

}
