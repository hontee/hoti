package com.hoti.site.db.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用Activity记录表操作
 * @author larry.qi
 *
 */
public interface TableIDs {
	
	String ACTIVITY = "t_activity";
	String BOOKMARK = "t_bookmark";
	String BOOKMARK_FOLLOW = "t_bookmark_follow";
	String CATEGORY = "t_category";
	String DOMAIN = "t_domain";
	String GROUP = "t_group";
	String GROUP_BOOKMARK = "t_group_bookmark";
	String GROUP_FOLLOW = "t_group_follow";
	String MENU = "t_menu";
	String MTYPE = "t_mtype";
	String RECOMMEND = "t_recommend";
	String TRACK = "t_track";
	String USER = "t_user";
	String REGION = "t_region";
	
	/**
	 * 获取表名信息
	 * @return
	 */
	public static List<String> getList() {
		List<String> list = new ArrayList<>();
		Field[] fields = TableIDs.class.getFields();
		Arrays.asList(fields).forEach((f) -> {
			try {
				list.add((String) f.get(f.getName()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return list;
	}
	
}
