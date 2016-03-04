package com.kuaiba.site.db.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Category implements Serializable {

	private static final long serialVersionUID = -3683571533871584084L;

	private Long id;

    private String name;

    private String title;

    private String description;

    private String avatar;

    private String cover;

    private Long count;

    private Byte state;

    private Date created;

    private Date lastModified;

    private Long domain;

    private Long createBy;
    
    private String extDomainTitle;
    
	private String extCreateName;
	
	private List<Website> websites;
	
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
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

	public Long getDomain() {
		return domain;
	}

	public void setDomain(Long domain) {
		this.domain = domain;
	}

	public String getExtDomainTitle() {
		return extDomainTitle;
	}

	public void setExtDomainTitle(String extDomainTitle) {
		this.extDomainTitle = extDomainTitle;
	}

	public String getExtCreateName() {
		return extCreateName;
	}

	public void setExtCreateName(String extCreateName) {
		this.extCreateName = extCreateName;
	}

	public List<Website> getWebsites() {
		return websites;
	}

	public void setWebsites(List<Website> websites) {
		this.websites = websites;
	}
    
}