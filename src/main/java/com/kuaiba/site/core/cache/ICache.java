package com.kuaiba.site.core.cache;

import java.util.ArrayList;
import java.util.List;

import com.kuaiba.site.db.entity.Category;

public interface ICache {

	@SuppressWarnings("unchecked")
	public static List<Category> findByCollect(CacheIds key) {
		try {
			Object obj = CacheUtils.get(key);
			if(obj != null) {
				return (List<Category>) obj;
			}
		} catch (Exception e) {
		}
		return new ArrayList<>();
	}
}
