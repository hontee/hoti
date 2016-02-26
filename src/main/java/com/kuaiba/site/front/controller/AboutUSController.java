package com.kuaiba.site.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 关于我们
 * @author larry.qi
 *
 */
@Controller
public class AboutUSController {
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
		return "views/about";
	}

}
