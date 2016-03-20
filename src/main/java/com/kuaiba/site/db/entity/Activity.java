package com.kuaiba.site.db.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Activity implements Serializable {

	private static final long serialVersionUID = 2396896112944949584L;
	
	/**
	 * 用户类型
	 */
	final static String[] userTypes = {"user", "admin"};
	
	/**
	 * 状态 1=操作成功 ，0=操作失败
	 */
	final static Byte[] states = {0, 1};

	private Long id;

    private String name;

    private String description;

    private String tbl;

    private String ip;

    private Byte state;

    private Date created;

    private String creator;

    private String userType;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTbl() {
        return tbl;
    }

    public void setTbl(String tbl) {
        this.tbl = tbl;
    }

    public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    /**
	 * 验证用户类型
	 * @param userType
	 * @return
	 */
	public static boolean checkUserType(String userType) {
		return Arrays.asList(userTypes).contains(userType);
	}
	
	/**
	 * 验证状态
	 * @param state
	 * @return
	 */
	public static boolean checkState(Byte state) {
		return Arrays.asList(states).contains(state);
	}
    
}