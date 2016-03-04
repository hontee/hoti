package com.kuaiba.site.front.vo;

public class BookmarkVO extends BaseVO {

	private static final long serialVersionUID = 7449613864826308903L;

	private String url;
	private Long category;

	private String reffer;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public String getReffer() {
		return reffer;
	}

	public void setReffer(String reffer) {
		this.reffer = reffer;
	}

}
