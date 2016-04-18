package com.hoti.site.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
	private Long groupCount;
	private Byte state;
	private Date created;
	private Date lastModified;
	private Long domain;
	private Long createBy;
	private String domainTitle;
	private String creator;
	
	private List<Bookmark> bookmarks;
	
	private List<Group> groups;

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
	
	public Long getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(Long groupCount) {
		this.groupCount = groupCount;
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

	public String getDomainTitle() {
		return domainTitle;
	}

	public void setDomainTitle(String domainTitle) {
		this.domainTitle = domainTitle;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public List<Bookmark> getBookmarks() {
		
		if (bookmarks == null) {
			bookmarks = new ArrayList<>();
		}
		
		return bookmarks;
	}

	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public List<Group> getGroups() {
		
		if (groups == null) {
			groups = new ArrayList<>();
		}
		
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
}