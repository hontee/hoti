package com.kuaiba.site.service;

/**
 * 命名服务
 * @author larry.qi
 *
 */
public interface NamedService {
	
	/**
	 * 验证Bookmark名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkBookmarkName(String name);
	
	/**
	 * 验证BookmarkURL是否存在
	 * @param url
	 * @return
	 */
	boolean checkBookmarkURL(String url);
	
	/**
	 * 验证Bookmark标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkBookmarkTitle(String title);
	
	/**
	 * 验证Category名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkCategoryName(String name);
	
	/**
	 * 验证Domain名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkDomainName(String name);
	
	/**
	 * 验证Domain标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkDomainTitle(String title);
	
	/**
	 * 验证Group名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkGroupName(String name);
	
	/**
	 * 验证Group标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkGroupTitle(String title);
	
	/**
	 * 验证Menu名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkMenuName(String name);
	
	/**
	 * 验证Menu标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkMenuTitle(String title);
	
	/**
	 * 验证MType名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkMTypeName(String name);
	
	/**
	 * 验证MType标题是否存在
	 * @param title
	 * @return
	 */
	boolean checkMTypeTitle(String title);
	
	/**
	 * 验证User名称是否存在
	 * @param name
	 * @return
	 */
	boolean checkUserName(String name);

}
