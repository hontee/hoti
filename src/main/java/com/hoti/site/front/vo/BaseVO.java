package com.hoti.site.front.vo;

import java.io.Serializable;
import java.util.UUID;

public class BaseVO implements Serializable {

	private static final long serialVersionUID = -4400433457697423053L;

	private String name;
	private String title;
	private String description = "无";
	private Byte state;

	public String getName() {
		return name;
	}
	
	public String getNameUUID() {
		return UUID.randomUUID().toString();
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

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

}
