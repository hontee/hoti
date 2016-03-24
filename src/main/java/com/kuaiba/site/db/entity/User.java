package com.kuaiba.site.db.entity;

import java.io.Serializable;
import java.util.Date;

import com.kuaiba.site.core.security.EncryptUtils;

public class User implements Serializable {

	private static final long serialVersionUID = -6588021396758305496L;
	
	private Long id;
    private String name;
    private String title;
    private String description;
    private String password;
    private String salt;
    private Byte userType;
    private Byte state;
    private Date created;
    private Date lastModified;

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
    
    public void setPasswordEncrypt(String password, String salt) {
        this.setPassword(EncryptUtils.encrypt(password, salt));
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public void setSaltRandom() {
        this.setSalt(EncryptUtils.getRandomSalt());
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
    	this.userType = userType;
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
    
}