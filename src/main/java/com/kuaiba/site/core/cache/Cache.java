package com.kuaiba.site.core.cache;

import com.kuaiba.site.core.exceptions.CacheException;

public interface Cache
{
    /**
     * Given a key name, return the corresponding value.
     *
     * @param key is the name used to store the entry.
     * @return entry stored in the cache.
     * @throws CacheException will wraps the implementation's exception.
     */
    Object get( CacheIds key ) throws CacheException;


    /**
     * Add a new entry to the cache.
     *
     * @param key name to be used for the entry.
     * @param value object that is stored.
     * @throws CacheException will wraps the implementation's exception.
     */
    void put( CacheIds key, Object value ) throws CacheException;


    /**
     * Clear a cache entry for a given name.
     *
     * @param key name that entry is stored as.
     * @return boolean value will be false if entry not found and true if entry was found and removed.
     * @throws CacheException will wraps the implementation's exception.
     */
    boolean clear( CacheIds key ) throws CacheException;
    
    /**
     * Contain a cache entry for a given name.
     *
     * @param key name that entry is stored as.
     * @return boolean value will be false if entry not found and true if entry was found.
     * @throws CacheException will wraps the implementation's exception.
     */
    boolean contains( CacheIds key ) throws CacheException;


    /**
     * Remove all entries from the cache.
     *
     * @throws CacheException will wraps the implementation's exception.
     */
    void flush() throws CacheException;
    
}