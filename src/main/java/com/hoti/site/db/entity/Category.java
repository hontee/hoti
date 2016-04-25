package com.hoti.site.db.entity;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String title;

  private String description;

  private String avatar;

  private String cover;

  private Long parent;
  
  private Long category;

  private Long product;

  private Long topic;

  private Long weight;

  private Byte state;

  private Date created;

  private Date lastModified;

  private String creator;

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

  public Long getParent() {
    return parent;
  }

  public void setParent(Long parent) {
    this.parent = parent;
  }

  public Long getCategory() {
    return category;
  }

  public void setCategory(Long category) {
    this.category = category;
  }

  public Long getProduct() {
    return product;
  }

  public void setProduct(Long product) {
    this.product = product;
  }

  public Long getTopic() {
    return topic;
  }

  public void setTopic(Long topic) {
    this.topic = topic;
  }

  public Long getWeight() {
    return weight;
  }

  public void setWeight(Long weight) {
    this.weight = weight;
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

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }
}
