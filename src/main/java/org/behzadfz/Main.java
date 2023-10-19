package org.behzadfz;

import java.util.concurrent.*;

import org.behzadfz.concurrency.CyclicBarrierTask;
import org.behzadfz.concurrency.Invoker;
import org.behzadfz.concurrency.Task;

public class Main {
    public static void main(String[] args) {

        // I. Executor
        Executor executor = new Invoker();

        try {
            // executor invokes the submitted task instantly in the invoking thread (main thread)
            executor.execute(()-> {
                System.out.println(Thread.currentThread());
            });
        } catch (RejectedExecutionException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Thread.currentThread());

        // II. ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new Task());
        executorService.shutdown();

        // III. ScheduledExecutorService
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        Future<String> future = scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread()+" from scheduledExecutorService");
            return Thread.currentThread()+" from scheduledExecutorService";
        },5,TimeUnit.SECONDS);

        System.out.println("status? => "+ future.isDone());

        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread() + "from scheduled future!");
        }, 8, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();

        ScheduledExecutorService anotherScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        anotherScheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("from at fixed rate");
        }, 1, 10, TimeUnit.SECONDS);

        anotherScheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println("from with fixed delay");
            System.out.println("status? => "+ future.isDone());

        }, 1, 10, TimeUnit.SECONDS);


        //IV. Future
        ExecutorService executorServiceIV = Executors.newFixedThreadPool(10);

        Future<String> futureIV = executorServiceIV.submit(() -> {

            Thread.sleep(10000l);
            return "from the future!!";
        });

        if (futureIV.isDone() && !futureIV.isCancelled()) {
            try {
                String str = futureIV.get(10, TimeUnit.SECONDS);
                System.out.println(str);
            } catch (InterruptedException | ExecutionException | TimeoutException exception) {
                exception.printStackTrace();
            }
        }

        // V. Cyclic Barrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {

            System.out.println("All previous tasks are completed!");
        });

        Thread t1 = new Thread(new CyclicBarrierTask(cyclicBarrier), "T1");
        Thread t2 = new Thread(new CyclicBarrierTask(cyclicBarrier), "T2");
        Thread t3 = new Thread(new CyclicBarrierTask(cyclicBarrier), "T3");

        if (!cyclicBarrier.isBroken()) {
            t1.start();
            t2.start();
            t3.start();
        }
        System.out.println("Terminate!");
    }
}
