package com.kuaiba.site.db.entity;

import java.io.Serializable;
import java.util.Date;

public class GroupBookmarkRelation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
    private String name;
    private String title;
    private String description;
    private Integer star;
    private Integer hit;
    private Byte state;
    private Byte audit;
    private Long category;
    private String cateTitle;
    private Long mtype;
    private String mtTitle;
    private Long gid;
    private Date created;
    private int follow; // 是否关注
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

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getCateTitle() {
        return cateTitle;
    }

    public void setCateTitle(String cateTitle) {
        this.cateTitle = cateTitle;
    }

    public Long getMtype() {
        return mtype;
    }

    public void setMtype(Long mtype) {
        this.mtype = mtype;
    }

    public String getMtTitle() {
        return mtTitle;
    }

    public void setMtTitle(String mtTitle) {
        this.mtTitle = mtTitle;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	public int getFollow() {
		return follow;
	}

	public void setFollow(int follow) {
		this.follow = follow;
	}

	public Mtype getMt() {
		
		if (mt == null) {
			mt = new Mtype();
		}
		return mt;
	}

	public void setMt(Mtype mt) {
		this.mt = mt;
	}
    
}