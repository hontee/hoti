package com.hoti.site.db.entity;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * 用于前端的分页查询
 * 
 * @author larry.qi
 */
public class Pager implements Serializable {

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
   * 设置分页路径
   * 
   * @param baseUrl
   * @param page
   * @return
   */
  private static String setPageNum(String baseUrl, int page) {
    try {
      if (baseUrl.contains("page=")) {
        Map<String, String> params = HttpUtil.getQueryParams(baseUrl);
        String oldPage = params.get("page");
        return baseUrl.replace(oldPage, String.valueOf(page));
      } else {
        return HttpUtil.appendQueryParams(baseUrl, "page=" + page);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  
    return "1";
  }

  /**
   * 生成分页实体数据
   * 
   * @param pageInfo
   * @param uri
   * @return
   */
  public static Pager of(PageInfo<?> pageInfo, String url) {
    return of(pageInfo, url, "");
  }

  /**
   * 生成分页实体数据（含查询关键字）
   * 
   * @param pageInfo
   * @param baseUrl
   * @param q
   * @return
   */
  public static Pager of(PageInfo<?> pageInfo, String baseUrl, String q) {
    Pager pu = new Pager();
    pu.setQ(q);
    pu.setTotal(pageInfo.getTotal());

    if (pageInfo.isHasPreviousPage()) { // 设置上一页
      pu.setPrevious(setPageNum(baseUrl, pageInfo.getPrePage()));
    }

    if (pageInfo.isIsFirstPage()) {
      pu.setPrevious(null);
    }

    if (pageInfo.isHasNextPage()) { // 设置下一页
      pu.setNext(setPageNum(baseUrl, pageInfo.getNextPage()));
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
