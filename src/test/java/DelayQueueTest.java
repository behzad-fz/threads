import org.behzadfz.concurrency.deleyqueue.DelayObject;
import org.behzadfz.concurrency.deleyqueue.DelayQueueConsumer;
import org.behzadfz.concurrency.deleyqueue.DelayQueueProducer;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class DelayQueueTest {
    @Test
    public void givenDelayQueue_whenProduceElement_thenShouldConsumeAfterGivenDelay() throws InterruptedException {
        // given
        ExecutorService executor = Executors.newFixedThreadPool(2);

        BlockingQueue<DelayObject> queue = new DelayQueue<>();
        int numberOfElementsToProduce = 2;
        int delayOfEachProducedMessageMilliseconds = 500;
        DelayQueueConsumer consumer = new DelayQueueConsumer(
                queue, numberOfElementsToProduce);
        DelayQueueProducer producer = new DelayQueueProducer(
                queue, numberOfElementsToProduce, delayOfEachProducedMessageMilliseconds);

        // when
        executor.submit(producer);
        executor.submit(consumer);

        // then
        executor.awaitTermination(5, TimeUnit.SECONDS);
        executor.shutdown();

        assertEquals(consumer.numberOfConsumedElements.get(), numberOfElementsToProduce);
    }
}
