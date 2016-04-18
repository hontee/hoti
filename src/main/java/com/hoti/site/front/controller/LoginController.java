package com.hoti.site.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hoti.site.core.security.AuthzUtil;

@Controller
@Scope("prototype")
public class LoginController {

  /**
   * 用户登录页
   * 
   * @return
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(HttpServletRequest request, Model model) {
    return AuthzUtil.isAuthorized() ? SiteUtil.redirect("/") : "login.ftl";
  }

  /**
   * 退出登录
   * 
   * @return
   */
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout() {
    SecurityUtils.getSubject().logout();
    return SiteUtil.redirect("/");
  }

}
