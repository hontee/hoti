package com.ikyer.site.front.vo;

public class ProductVO extends BaseVO {

  private static final long serialVersionUID = 7449613864826308903L;

  private String url;

  private String reffer;

  private String tags;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getReffer() {
    return reffer;
  }

  public void setReffer(String reffer) {
    this.reffer = reffer;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

}
