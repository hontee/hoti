package com.kuaiba.site.db.entity;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {

	private static final long serialVersionUID = 1864047863059201155L;

	private Long id;

    private String name;

    private String title;

    private String description;

    private String gtype;

    private String avatar;

    private String cover;

    private Integer stars;

    private Integer count;

    private Byte state;

    private Date created;

    private Date lastModified;

    private Long createBy;

    private Long mtype;

    private Long category;
    
    private String extCategoryTitle;
    
    private String extCreateName;

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

    public String getGtype() {
        return gtype;
    }

    public void setGtype(String gtype) {
        this.gtype = gtype;
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

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public Long getMtype() {
        return mtype;
    }

    public void setMtype(Long mtype) {
        this.mtype = mtype;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

	public String getExtCategoryTitle() {
		return extCategoryTitle;
	}

	public void setExtCategoryTitle(String extCategoryTitle) {
		this.extCategoryTitle = extCategoryTitle;
	}

	public String getExtCreateName() {
		return extCreateName;
	}

	public void setExtCreateName(String extCreateName) {
		this.extCreateName = extCreateName;
	}
    
}