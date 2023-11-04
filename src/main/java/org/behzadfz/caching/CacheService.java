package org.behzadfz.caching;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
public class CacheService {
    private Cache<String, Object> cache;

    public CacheService() {
        cache = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.getIfPresent(key);
    }

    public void forget(String key) {
        cache.invalidate(key);
    }
}
