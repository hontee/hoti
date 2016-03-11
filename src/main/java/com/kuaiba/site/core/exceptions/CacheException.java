package com.kuaiba.site.core.exceptions;

public class CacheException extends CoreException {

	private static final long serialVersionUID = 5229919838913169942L;

	public CacheException() {
		super(ExceptionIds.CACHE_DEFAULT);
	}

	public CacheException(ExceptionIds ex) {
		super(ex);
	}
	
}
