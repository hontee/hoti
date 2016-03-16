package com.kuaiba.site.core.cache;

import com.kuaiba.site.core.exception.BaseRuntimeException;
import com.kuaiba.site.core.exception.CacheException;
import com.kuaiba.site.core.exception.ErrorIDs;
import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.core.exception.ValidationException;

import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.BlockingCache;

class EhCacheImpl implements Cache {
	
	private BlockingCache cache;
	
	EhCacheImpl(BlockingCache blockingCache) {
		
		if (blockingCache == null) {
			throw new BaseRuntimeException(ErrorIDs.CACHE_ERROR, "BlockingCache为空");
		}
		
		this.cache = blockingCache;
	}

	@Override
	public Object get(CacheIDs key) throws SecurityException {
		
		if (key == null) {
			throw new ValidationException("获取缓存失败：CacheID为空");
		}

		try {
			Element element = cache.get(key);
			return (element != null)? element.getObjectValue(): null;
		} catch (Exception e) {
			throw new CacheException("获取缓存失败", e);
		}
	}

	@Override
	public void put(CacheIDs key, Object value) throws SecurityException {
		
		if (key == null) {
			throw new ValidationException("设置缓存失败：CacheID为空");
		}
		
		try {
			// 如果已存在缓存，先清除再设置
			if (contains(key)) {
				clear(key);
			}
			cache.put(new Element(key, value));
		} catch (Exception e) {
			throw new CacheException("设置缓存失败", e);
		}

	}

	@Override
	public boolean clear(CacheIDs key) throws SecurityException {
		
		if (key == null) {
			throw new ValidationException("移除缓存失败：CacheID为空");
		}
		
		try {
			return cache.remove(key);
		} catch (Exception e) {
			throw new CacheException("移除缓存失败", e);
		}
	}

	@Override
	public boolean contains(CacheIDs key) throws SecurityException {

		if (key == null) {
			throw new ValidationException("检查缓存失败：CacheID为空");
		}
		
		try {
			return cache.getKeys().contains(key);
		} catch (Exception e) {
			throw new CacheException("检查缓存失败", e);
		}
	}

	@Override
	public void flush() throws SecurityException {
		
		try {
			cache.removeAll();
		} catch (Exception e) {
			throw new CacheException("刷新缓存失败", e);
		}

	}

}
