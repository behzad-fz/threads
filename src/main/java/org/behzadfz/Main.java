package org.behzadfz;

import java.util.concurrent.*;

import org.behzadfz.concurrency.Invoker;
import org.behzadfz.concurrency.Task;

public class Main {
    public static void main(String[] args) {

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


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new Task());
        executorService.shutdown();

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        Future<String> future = scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread()+" from scheduledExecutorService");
            return Thread.currentThread()+" from scheduledExecutorService";
        },5,TimeUnit.SECONDS);

        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread() + "from scheduled future!");
        }, 8, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();

        System.out.println("Terminate!");
    }
}
