package org.behzadfz.caching;

public class ThreadSafeCounterService {
    private final CacheService cacheService;

    public ThreadSafeCounterService() {
        this.cacheService = new CacheService();
    }

    public synchronized void increase() {
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
