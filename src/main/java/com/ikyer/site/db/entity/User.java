package com.ikyer.site.db.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.ikyer.site.core.security.EncryptUtil;

public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String title;

  private String description;

  private String password;

  private String salt;

  private Byte type;

  private Byte state;

  private Date created;

  private Date lastModified;
  
  /**
   * fid & ftime 关注主题/产品用户的映射
   */
  private Long fid;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  /**
   * 密码加密，同时会自动生成一个随机Salt值
   * @param password
   */
  public void setPasswordEncrypt(String password) {
    
    /* 随机生成密码盐值 */
    if (StringUtils.isEmpty(getSalt())) {
      setSalt(UUID.randomUUID().toString().toUpperCase());
    }
    
    this.password = EncryptUtil.encrypt(password, getSalt());
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
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

  public Long getFid() {
    return fid;
  }

  public void setFid(Long fid) {
    this.fid = fid;
  }

  public Date getFtime() {
    return ftime;
  }

  public void setFtime(Date ftime) {
    this.ftime = ftime;
  }
  
}
