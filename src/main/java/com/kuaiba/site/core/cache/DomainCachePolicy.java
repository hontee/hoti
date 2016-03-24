package com.kuaiba.site.core.cache;

import java.util.List;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.db.entity.Domain;

/**
 * 领域缓存策略
 * @author larry.qi
 *
 */
public interface DomainCachePolicy {
	
	/**
	 * 获取所有领域
	 * @return
	 * @throws SecurityException
	 */
	public List<Domain> getDomains() throws SecurityException;

}
