package com.kuaiba.site.db.entity;

import java.util.Date;

public class Website {
    private Long id;

    private String name;

    private String title;

    private String url;

    private String description;

    private String avatar;

    private String reffer;

    private Integer star;

    private Integer hit;

    private Byte state;

    private Byte audit;

    private Date created;

    private Date lastModified;

    private Long createBy;

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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getAudit() {
        return audit;
    }

    public void setAudit(Byte audit) {
        this.audit = audit;
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