import org.behzadfz.concurrency.countdownlatch.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

public class WorkerCountDownLatchTest {
    @Test
    public void testBlocksThreadUntilCounterReachesZero() throws InterruptedException{

        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);

        List<Thread> workers = Stream.generate(
            () -> new Thread(new Worker(outputScraper, countDownLatch))
        )
            .limit(5)
            .collect(toList());

        workers.forEach(Thread::start);
        countDownLatch.await();
        outputScraper.add("Latch released!");

        assertAll(
                ()-> assertEquals("Counted down", outputScraper.get(0)),
                ()-> assertEquals("Counted down", outputScraper.get(1)),
                ()-> assertEquals("Counted down", outputScraper.get(2)),
                ()-> assertEquals("Counted down", outputScraper.get(3)),
                ()-> assertEquals("Counted down", outputScraper.get(4)),
                ()-> assertEquals("Latch released!", outputScraper.get(5))
        );
    }
}
