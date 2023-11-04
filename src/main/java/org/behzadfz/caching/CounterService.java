package org.behzadfz.caching;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class CounterService {
    private final CacheService cacheService;

    public CounterService() {
        this.cacheService = new CacheService();
    }

    public void increase() {
        Integer value = (Integer) cacheService.get("counter");

        if (value == null) {
            value = 0;
        }
        cacheService.put("counter", value + 1);
    }

    public int getCounter() {
        return (int) cacheService.get("counter");
    }
}
