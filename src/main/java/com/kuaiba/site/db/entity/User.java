package com.kuaiba.site.db.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.kuaiba.site.core.security.EncryptUtils;

public class User implements Serializable {

	private static final long serialVersionUID = -6588021396758305496L;
	
	final Byte[] userTypes = {1, 2};
	final Byte[] states = {0, 1, 2, 3};
	
	// 用户类型
	protected enum UserType {
		ADMIN, USER
	}
	
	// 用户状态
	protected enum UserState {
		DISABLED, ACTIVE, LOCKED, DELETED
	}
	
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

    /**
     * 设置用户类型，如果值不在范围内，使用默认值
     * @param userType
     */
    public void setUserType(Byte userType) {
    	this.userType = userType;
    	if (!Arrays.asList(userTypes).contains(userType)) {
			setUserType(UserType.USER);
		}
    }
    
    /**
     * 设置用户类型
     * @param userType
     */
    public void setUserType(UserType userType) {
        int index = userType.ordinal();
        this.userType = (byte)index++;
    }

    public Byte getState() {
        return state;
    }

    /**
     * 设置用户状态，如果不在范围内，使用默认值。
     * @param state
     */
    public void setState(Byte state) {
        this.state = state;
        if (!Arrays.asList(states).contains(state)) {
        	setState(UserState.ACTIVE);
		}
    }
    
    /**
     * 设置用户状态
     * @param state
     */
    public void setState(UserState state) {
        this.userType = (byte) state.ordinal();
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