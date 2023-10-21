package org.behzadfz.concurrency.blockingqueue;

import org.behzadfz.advanced.Week;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class NumbersConsumer implements Runnable {
    private BlockingQueue<Integer> queue;
    private final int poisonPill;

    public NumbersConsumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }


    public void run() {

        Week.SUNDAY.name();
        Week.SUNDAY.getValue();

        for (Week week: Week.values()) {
            String combo = week.getValue() + week.name();
        }






        try {
            while (true) {
                Integer number = queue.take();
                if (number.equals(poisonPill)) {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " result: " + number);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}