
import org.behzadfz.caching.CacheService;
import org.behzadfz.caching.CounterService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {

    @Test
    public void testPutsAndGetsFromCache() throws Exception {
        CacheService cacheService = new CacheService();

        Thread setValue = new Thread(() -> {
            cacheService.put("foo", "bar");
        });

        Thread getValue = new Thread(() -> {
            assertEquals(cacheService.get("foo"), "bar");
        });

        setValue.start();
        setValue.join();
        getValue.start();
    }

    @Test
    public void testRemovesFromCache() throws Exception {
        CacheService cacheService = new CacheService();
        cacheService.put("foo", "bar");
        assertEquals(cacheService.get("foo"), "bar");

        cacheService.forget("foo");

        assertEquals(cacheService.get("foo"), null);
    }

    @Test
    public void testThreadUnsafeCache() throws Exception {
        CounterService counter = new CounterService();

        Thread a = new Thread(() -> {
            for (int i = 0;i < 5; i++) {
                counter.increase();
            }
        });

        Thread b = new Thread(() -> {
            for (int i = 0;i < 5; i++) {
                counter.increase();
            }
        });

        a.start();
        b.start();

        a.join();
        b.join();

        // counter should be 10 in a single thread app but not guarantee in multi thread app
        assertNotEquals(counter.getCounter(), 10);
    }
}
