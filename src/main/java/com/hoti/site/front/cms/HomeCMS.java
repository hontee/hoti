package com.hoti.site.front.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hoti.site.core.exception.SecurityException;

@Controller
@RequestMapping("/cms")
public class HomeCMS {

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String home() throws SecurityException {
    return "cms/home";
  }

}
