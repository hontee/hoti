package com.kuaiba.site.core.cache;

import java.net.URL;

import com.kuaiba.site.core.exception.BaseRuntimeException;
import com.kuaiba.site.core.exception.ErrorIDs;
import com.kuaiba.site.db.entity.GlobalIDs;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.blocking.BlockingCache;

public class CacheFactory {
	
	/**
	 * Create and return a reference to {@link Cache} object.
	 *
	 * @return instance of {@link Cache}.
	 */
	public static Cache createInstance(String name) {
		
		try {
			URL url = CacheFactory.class.getResource(GlobalIDs.CFG_EHCACHE);
			CacheManager cacheManager = CacheManager.create(url);
			Ehcache cache = cacheManager.getEhcache(name);
			BlockingCache blockingCache = new BlockingCache(cache);
			cacheManager.replaceCacheWithDecoratedCache(cache, blockingCache);
			return new EhCacheImpl(blockingCache);
		} catch (Exception e) {
			throw new BaseRuntimeException(ErrorIDs.CACHE_ERROR, "创建缓存实例失败", e);
		}
	}
	
	/**
	 * 创建对象缓存
	 * @return
	 */
	public static Cache createObjectCache() {
		return createInstance(GlobalIDs.CACHE_OBJECTS);
	}

}
