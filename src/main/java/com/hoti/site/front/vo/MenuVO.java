package com.hoti.site.front.vo;

public class MenuVO extends BaseVO {

	private static final long serialVersionUID = 6722667839714686690L;

	private String path;
	private String organization;
	private Integer weight;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}
