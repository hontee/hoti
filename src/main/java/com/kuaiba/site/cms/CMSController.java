package com.kuaiba.site.cms;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cms")
public class CMSController {
	
	private static final Logger logger = LoggerFactory.getLogger(CMSController.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome to Kuaiba CMS System!");
		return "cms/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Goto Kuaiba CMS Login page.");
		return "cms/login";
	}
	
}
