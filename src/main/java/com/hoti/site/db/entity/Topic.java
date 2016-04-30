package com.hoti.site.db.entity;

import java.io.Serializable;
import java.util.Date;

public class Topic implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String title;

  private String description;

  private String avatar;

  private String cover;

  private Integer star;

  private Integer product;

  private Byte audit;

  private Byte pick;

  private Byte type;

  private Byte state;

  private Date created;

  private Date lastModified;

  private Long createBy;

  private String creator;
  
  /*判断用户是否关注*/
  private int follow;
  
  /**
   * uid & ftime 是用户关注主题的映射
   */
  private Long uid;
  private Date ftime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }

  public Integer getStar() {
    return star;
  }

  public void setStar(Integer star) {
    this.star = star;
  }

  public Integer getProduct() {
    return product;
  }

  public void setProduct(Integer product) {
    this.product = product;
  }

  public Byte getAudit() {
    return audit;
  }

  public void setAudit(Byte audit) {
    this.audit = audit;
  }

  public Byte getPick() {
    return pick;
  }

  public void setPick(Byte pick) {
    this.pick = pick;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  public Byte getState() {
    return state;
  }

  public void setState(Byte state) {
    this.state = state;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public Long getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public int getFollow() {
    return follow;
  }

  public void setFollow(int follow) {
    this.follow = follow;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Date getFtime() {
    return ftime;
  }

  public void setFtime(Date ftime) {
    this.ftime = ftime;
  }
  
}
