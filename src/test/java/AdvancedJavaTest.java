import org.behzadfz.advanced.Week;
import org.behzadfz.concurrency.blockingqueue.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdvancedJavaTest {

    @Test
    public void testItWorks() throws Exception {
        assertEquals("SUNDAY",Week.SUNDAY.name());
        assertEquals("Sunday",Week.SUNDAY.getValue());

        assertArrayEquals(
            new Week[]{Week.SATURDAY, Week.SUNDAY},
            Week.values()
        );

        App app = new App();
        app.addVersion("2.1");

        assertTrue(app.getVersions().contains("2.1"));

        Exception exception = assertThrows(Exception.class, () -> {
            App appl = new App();
            appl.addVersion("2.0");
        });

        assertEquals("Invalid version: 2.0", exception.getMessage());
    }
}
