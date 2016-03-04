package com.kuaiba.site.db.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Organization implements Serializable {

	private static final long serialVersionUID = -7060569167593709706L;

	private Long id;

    private String name;

    private String title;

    private String description;

    private String avatar;

    private Integer weight;

    private Byte state;

    private Date created;

    private Date lastModified;

    private String creator;
    
    private List<Category> cates;

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
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

	public List<Category> getCates() {
		return cates;
	}

	public void setCates(List<Category> cates) {
		this.cates = cates;
	}
    
}