package com.kuaiba.site.core.cache;

import java.net.URL;

import com.kuaiba.site.core.GlobalIDs;
import com.kuaiba.site.core.exceptions.CacheException;
import com.kuaiba.site.core.exceptions.ExceptionIds;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.blocking.BlockingCache;

public class CacheFactory
{
    /**
     * Create and return a reference to {@link Cache} object.
     *
     * @return instance of {@link Cache}.
     */
    public static Cache createInstance(String name)
    {
    	try {
			URL url = CacheFactory.class.getResource(GlobalIDs.CFG_EHCACHE);
			CacheManager cacheManager = CacheManager.create(url);
			Ehcache cache = cacheManager.getEhcache(name);
			BlockingCache blockingCache = new BlockingCache(cache);
			cacheManager.replaceCacheWithDecoratedCache(cache, blockingCache);
			return new EhCacheImpl(blockingCache);
		} catch (Exception e) {
			throw new CacheException(ExceptionIds.CACHE_CREATE_INSTANCE_ERROR);
		}
    }
    
}
