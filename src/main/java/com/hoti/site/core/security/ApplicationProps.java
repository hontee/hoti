package com.hoti.site.core.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProps implements Serializable {

  private static final long serialVersionUID = 1L;

  @Value("${sys.charset}")
  private String charset;

  @Value("${sys.user}")
  private String loginUser;

  @Value("${sys.admin}")
  private String adminUser;

  @Value("${sys.reffer}")
  private String reffer;

  @Value("${sys.cookie.age}")
  private Integer cookieAge;

  @Value("${sys.fetch.agent}")
  private String fetchUserAgent;

  @Value("${sys.fetch.timeout}")
  private Integer fetchTimeout;

  @Value("${sys.front.rows}")
  private Integer frontRows;

  @Value("${memcached.host}")
  private String memcachedHost;

  @Value("${memcached.port}")
  private String memcachedPort;

  @Value("${seo.slogan}")
  private String seoSlogan;

  @Value("${seo.login}")
  private String seoLogin;

  @Value("${seo.topic}")
  private String seoTopic;

  @Value("${seo.search.product}")
  private String seoSearchProduct;

  @Value("${seo.search.topic}")
  private String seoSearchTopic;

  @Value("${seo.about}")
  private String seoAbout;

  @Value("${seo.recommend}")
  private String seoRecommend;

  public String getCharset() {
    return charset;
  }

  public void setCharset(String charset) {
    this.charset = charset;
  }

  public String getLoginUser() {
    return loginUser;
  }

  public void setLoginUser(String loginUser) {
    this.loginUser = loginUser;
  }

  public String getAdminUser() {
    return adminUser;
  }

  public void setAdminUser(String adminUser) {
    this.adminUser = adminUser;
  }

  public String getReffer() {
    return reffer;
  }

  public void setReffer(String reffer) {
    this.reffer = reffer;
  }

  public Integer getCookieAge() {
    return cookieAge;
  }

  public void setCookieAge(Integer cookieAge) {
    this.cookieAge = cookieAge;
  }

  public String getFetchUserAgent() {
    return fetchUserAgent;
  }

  public void setFetchUserAgent(String fetchUserAgent) {
    this.fetchUserAgent = fetchUserAgent;
  }

  public Integer getFetchTimeout() {
    return fetchTimeout;
  }

  public void setFetchTimeout(Integer fetchTimeout) {
    this.fetchTimeout = fetchTimeout;
  }

  public Integer getFrontRows() {
    return frontRows;
  }

  public void setFrontRows(Integer frontRows) {
    this.frontRows = frontRows;
  }

  public String getMemcachedHost() {
    return memcachedHost;
  }

  public void setMemcachedHost(String memcachedHost) {
    this.memcachedHost = memcachedHost;
  }

  public String getMemcachedPort() {
    return memcachedPort;
  }

  public void setMemcachedPort(String memcachedPort) {
    this.memcachedPort = memcachedPort;
  }

  public String getSeoSlogan() {
    return seoSlogan;
  }

  public void setSeoSlogan(String seoSlogan) {
    this.seoSlogan = seoSlogan;
  }

  public String getSeoLogin() {
    return seoLogin;
  }

  public void setSeoLogin(String seoLogin) {
    this.seoLogin = seoLogin;
  }

  public String getSeoTopic() {
    return seoTopic;
  }

  public void setSeoTopic(String seoTopic) {
    this.seoTopic = seoTopic;
  }

  public String getSeoSearchProduct() {
    return seoSearchProduct;
  }

  public void setSeoSearchProduct(String seoSearchProduct) {
    this.seoSearchProduct = seoSearchProduct;
  }

  public String getSeoSearchTopic() {
    return seoSearchTopic;
  }

  public void setSeoSearchTopic(String seoSearchTopic) {
    this.seoSearchTopic = seoSearchTopic;
  }

  public String getSeoAbout() {
    return seoAbout;
  }

  public void setSeoAbout(String seoAbout) {
    this.seoAbout = seoAbout;
  }

  public String getSeoRecommend() {
    return seoRecommend;
  }

  public void setSeoRecommend(String seoRecommend) {
    this.seoRecommend = seoRecommend;
  }

}
