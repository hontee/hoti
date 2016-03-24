package com.kuaiba.site.db.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.RandomUtils;

public class Bookmark implements Serializable {

	private static final long serialVersionUID = 4078788641734464984L;

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
    private Long mtype;
    private String cateTitle;
    private String creator;
    private int extFollow;
    private Mtype mt;
    
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
    
    /**
     * 生成一个随机点击率
     */
    public void setHitRandom() {
        this.hit = RandomUtils.nextInt(0, 1000);
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

    public Long getMtype() {
        return mtype;
    }

    public void setMtype(Long mtype) {
        this.mtype = mtype;
    }

	public String getCateTitle() {
		return cateTitle;
	}

	public void setCateTitle(String cateTitle) {
		this.cateTitle = cateTitle;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getExtFollow() {
		return extFollow;
	}

	public void setExtFollow(int extFollow) {
		this.extFollow = extFollow;
	}
	
	public Mtype getMt() {
		return mt;
	}

	public void setMt(Mtype mt) {
		this.mt = mt;
	}
    
}