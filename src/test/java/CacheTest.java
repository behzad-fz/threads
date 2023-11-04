
import org.behzadfz.caching.CacheService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {

    @Test
    public void testItWorks() throws Exception {
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
}
