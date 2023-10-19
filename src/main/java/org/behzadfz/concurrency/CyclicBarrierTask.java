package org.behzadfz.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTask implements Runnable{
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierTask(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "is waiting");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + "is released");
        } catch (InterruptedException | BrokenBarrierException exception) {
            exception.printStackTrace();
        }
    }
}
