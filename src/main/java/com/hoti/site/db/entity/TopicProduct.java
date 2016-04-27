package com.hoti.site.db.entity;

import java.io.Serializable;

public class TopicProduct implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long tid;

  private String title;

  private Long cid;

  private Byte state;

  private Byte pick;
  
  public TopicProduct(Long tid) {
    this.tid = tid;
  }

  public Long getTid() {
    return tid;
  }

  public void setTid(Long tid) {
    this.tid = tid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getCid() {
    return cid;
  }

  public void setCid(Long cid) {
    this.cid = cid;
  }

  public Byte getState() {
    return state;
  }

  public void setState(Byte state) {
    this.state = state;
  }

  public Byte getPick() {
    return pick;
  }

  public void setPick(Byte pick) {
    this.pick = pick;
  }

}
