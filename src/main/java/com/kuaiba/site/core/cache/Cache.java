package com.kuaiba.site.core.cache;

import com.kuaiba.site.core.exception.SecurityException;

public interface Cache
{
    /**
     * Given a key name, return the corresponding value.
     *
     * @param key is the name used to store the entry.
     * @return entry stored in the cache.
     * @throws SecurityException will wraps the implementation's exception.
     */
    Object get( CacheIDs key ) throws SecurityException;


    /**
     * Add a new entry to the cache.
     *
     * @param key name to be used for the entry.
     * @param value object that is stored.
     * @throws SecurityException will wraps the implementation's exception.
     */
    void put( CacheIDs key, Object value ) throws SecurityException;


    /**
     * Clear a cache entry for a given name.
     *
     * @param key name that entry is stored as.
     * @return boolean value will be false if entry not found and true if entry was found and removed.
     * @throws SecurityException will wraps the implementation's exception.
     */
    boolean clear( CacheIDs key ) throws SecurityException;
    
    /**
     * Contain a cache entry for a given name.
     *
     * @param key name that entry is stored as.
     * @return boolean value will be false if entry not found and true if entry was found.
     * @throws SecurityException will wraps the implementation's exception.
     */
    boolean contains( CacheIDs key ) throws SecurityException;


    /**
     * Remove all entries from the cache.
     *
     * @throws SecurityException will wraps the implementation's exception.
     */
    void flush() throws SecurityException;
    
}