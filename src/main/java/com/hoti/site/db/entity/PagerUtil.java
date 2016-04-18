package com.hoti.site.db.entity;

import java.io.Serializable;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 用于前端的分页查询
 * @author larry.qi
 */
public class PagerUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// 总记录数
	private long total;
	// 上一页链接
	private String previous;
	// 下一页链接
	private String next;
    // 搜索关键词
    private String q;
	
	/**
	 * 执行分页任务
	 * @param p
	 */
	public static void startPage(Pagination p) {
		PageHelper.startPage(p.getPage(), p.getRows(), p.getOrderByClause());
	}
	
	/**
	 * 生成分页实体数据
	 * @param pageInfo
	 * @param uri
	 * @return
	 */
	public static PagerUtil of(PageInfo<?> pageInfo, String url) {
		return of(pageInfo, url, "");
	}
	
	/**
	 * 生成分页实体数据（含查询关键字）
	 * @param pageInfo
	 * @param baseUrl
	 * @param q
	 * @return
	 */
	public static PagerUtil of(PageInfo<?> pageInfo, String baseUrl, String q) {
		PagerUtil pu = new PagerUtil();
		pu.setQ(q);
		pu.setTotal(pageInfo.getTotal());
		
		if (pageInfo.isHasPreviousPage()) { // 设置上一页
			String previousPage = HttpUtil.appendQueryParams(baseUrl, "page=" + pageInfo.getPrePage());
			pu.setPrevious(previousPage);
		}
		
		if (pageInfo.isIsFirstPage()) {
			pu.setPrevious(null);
		}
		
		if (pageInfo.isHasNextPage()) { // 设置下一页
			String previousPage = HttpUtil.appendQueryParams(baseUrl, "page=" + pageInfo.getNextPage());
			pu.setNext(previousPage);
		}
		
		if (pageInfo.isIsLastPage()) {
			pu.setNext(null);
		}
		
		return pu;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

}
