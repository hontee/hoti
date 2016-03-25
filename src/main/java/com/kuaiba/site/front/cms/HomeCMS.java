package com.kuaiba.site.front.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cms")
public class HomeCMS {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home() {
		return "cms/home";
	}

}
