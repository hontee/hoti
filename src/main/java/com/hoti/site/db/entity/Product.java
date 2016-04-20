package com.hoti.site.db.entity;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String title;

  private String url;

  private String description;

  private String tags;

  private String avatar;

  private String reffer;

  private Integer star;

  private Integer hit;

  private Byte pick;

  private Byte audit;

  private Byte state;

  private Date created;

  private Date lastModified;

  private Long createBy;

  private String creator;

  private Long cid;

  private String category;
  
  /*判断用户是否关注*/
  private int follow;
  
  /**
   * uid & ftime 是用户关注产品的映射
   * tid & ftime 是主题产品的映射
   */
  private Long tid;
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getReffer() {
    return reffer;
  }

  public void setReffer(String reffer) {
    this.reffer = reffer;
  }

  public Integer getStar() {
    return star;
  }

  public void setStar(Integer star) {
    this.star = star;
  }

  public Integer getHit() {
    return hit;
  }

  public void setHit(Integer hit) {
    this.hit = hit;
  }

  public Byte getPick() {
    return pick;
  }

  public void setPick(Byte pick) {
    this.pick = pick;
  }

  public Byte getAudit() {
    return audit;
  }

  public void setAudit(Byte audit) {
    this.audit = audit;
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

  public Long getCid() {
    return cid;
  }

  public void setCid(Long cid) {
    this.cid = cid;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
  
  public int getFollow() {
    return follow;
  }

  public void setFollow(int follow) {
    this.follow = follow;
  }

  public Long getTid() {
    return tid;
  }

  public void setTid(Long tid) {
    this.tid = tid;
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
