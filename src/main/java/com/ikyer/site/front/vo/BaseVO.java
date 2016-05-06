package com.ikyer.site.front.vo;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.ikyer.site.db.entity.GlobalIDs;
import com.ikyer.site.db.entity.User;

public class BaseVO implements Serializable {

  private static final long serialVersionUID = -4400433457697423053L;

  private String name;
  private String title;
  private String description = "无";
  private Byte state;
  
  private Long uid;
  private String creator;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    
    if (StringUtils.isEmpty(name)) {
      setName(UUID.randomUUID().toString());
    }
    
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

  public Byte getState() {
    return state;
  }

  public void setState(Byte state) {
    this.state = state;
  }

  public Long getUid() {
    return (uid == null)? getUserId(): uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getCreator() {
    return (creator == null)? getUserName(): creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }
  
  /**
   * 当前用户是否登录, 未登录返回false
   * @return
   */
  public boolean isAuthorized() {
    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    return session.getAttribute(GlobalIDs.loginUser()) != null;
  }

  /**
   * 获取当前登录用户, 未登录则返回 NULL
   * @return
   */
  public User getCurrentUser() {

    if (!isAuthorized()) {
      return new User(-1L, "anonymous");
    }

    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    return (User) session.getAttribute(GlobalIDs.loginUser());
  }

  /**
   * 获取当前登录用户名, 如果当前用户未登录则返回 "anonymous".
   * @return
   */
  public String getUserName() {
    return getCurrentUser().getName();
  }

  /**
   * 取得当前用户的登录id
   */
  public Long getUserId() {
    return getCurrentUser().getId();
  }
  
}
