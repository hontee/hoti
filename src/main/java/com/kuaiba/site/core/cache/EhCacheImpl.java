package com.kuaiba.site.core.cache;

import com.kuaiba.site.core.exceptions.CacheException;
import com.kuaiba.site.core.exceptions.ExceptionIds;

import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.BlockingCache;

class EhCacheImpl implements Cache {
	
	private BlockingCache cache;
	
	EhCacheImpl( BlockingCache blockingCache )
    {
        if ( blockingCache == null )
        {
        	throw new CacheException(ExceptionIds.CACHE_CONSTRUCTOR_NULL);
        }
        this.cache = blockingCache;
    }

	@Override
	public Object get(CacheIDs key) throws CacheException {
		
		if (key == null) {
			throw new CacheException(ExceptionIds.CACHE_KEY_NULL);
		}

		try {
			Element element = cache.get(key);
			if (element != null) {
				return element.getObjectValue();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new CacheException(ExceptionIds.CACHE_GET_ERROR);
		}
	}

	@Override
	public void put(CacheIDs key, Object value) throws CacheException {
		
		if (cache == null) {
			throw new CacheException(ExceptionIds.CACHE_KEY_NULL);
		}
		
		try {
			cache.put(new Element(key, value));
		} catch (Exception e) {
			throw new CacheException(ExceptionIds.CACHE_GET_ERROR);
		}

	}

	@Override
	public boolean clear(CacheIDs key) throws CacheException {
		
		if (cache == null) {
			throw new CacheException(ExceptionIds.CACHE_KEY_NULL);
		}
		
		try {
			return cache.remove(key);
		} catch (Exception ce) {
			throw new CacheException(ExceptionIds.CACHE_CLEAR_ERROR);
		}
	}

	@Override
	public boolean contains(CacheIDs key) throws CacheException {
		if (cache == null) {
			throw new CacheException(ExceptionIds.CACHE_KEY_NULL);
		}
		
		try {
			return cache.getKeys().contains(key);
		} catch (Exception ce) {
			throw new CacheException(ExceptionIds.CACHE_CHECK_ERROR);
		}
	}

	@Override
	public void flush() throws CacheException {
		
		if (cache == null) {
			throw new CacheException(ExceptionIds.CACHE_KEY_NULL);
		}
		
		try {
			cache.removeAll();
		} catch (Exception ce) {
			throw new CacheException(ExceptionIds.CACHE_FLUSH_ERROR);
		}

	}

}
