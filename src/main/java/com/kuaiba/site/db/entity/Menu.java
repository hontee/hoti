package com.kuaiba.site.db.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Menu implements Serializable {

	private static final long serialVersionUID = 2606247721778487587L;
	
	/**
	 * 状态 1=启用 ，0=禁用
	 */
	final static Byte[] states = {0, 1};

	private Long id;

    private String name;

    private String title;

    private String description;

    private String organization;

    private String path;

    private Byte state;

    private Integer weight;

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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
    
	/**
	 * 验证状态
	 * @param state
	 * @return
	 */
	public static boolean checkState(Byte state) {
		return Arrays.asList(states).contains(state);
	}
}