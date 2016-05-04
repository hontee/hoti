package com.hoti.site.front.vo;

public class RecommendVO extends BaseVO {

  private static final long serialVersionUID = 3429186046429753835L;

  private String url;
  private String keywords;
  private Long tid;
  private String topic;
  
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Long getTid() {
    return tid;
  }

  public void setTid(Long tid) {
    this.tid = tid;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

}
