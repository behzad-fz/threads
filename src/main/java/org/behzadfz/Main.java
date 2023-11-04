package org.behzadfz;

import java.io.*;
import java.util.concurrent.*;

import org.behzadfz.caching.CacheService;
import org.behzadfz.concurrency.CustomThreadFactory;
import org.behzadfz.concurrency.CyclicBarrierTask;
import org.behzadfz.concurrency.Invoker;
import org.behzadfz.concurrency.Task;
import org.behzadfz.concurrency.blockingqueue.NumbersConsumer;
import org.behzadfz.concurrency.blockingqueue.NumbersProducer;
import org.behzadfz.advanced.SomeClassToSerilize;

public class Main {
    static Semaphore semaphore = new Semaphore(2);
    public static void main(String[] args) throws InterruptedException {

        CacheService cacheService = new CacheService();

        Thread setValue = new Thread(() -> {
            cacheService.put("foo", "bar");
        });

        setValue.start();


//        SomeClassToSerilize someClass = new SomeClassToSerilize(1, "the name");
//        someClass.setSecret(12);
//
//        String filename = "/Users/behzad_fz/Java/SpringBoot/threads/test-serialize.txt";
//        FileOutputStream fileOutputStream;
//        ObjectOutputStream objectOutputStream;
//
//        // Write to file
//        try {
//            fileOutputStream = new FileOutputStream(filename);
//            objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(someClass);
//
//            objectOutputStream.close();
//            fileOutputStream.close();
//            System.out.println("Serilized!!");
//        } catch (IOException e) {
//            System.out.println("IO Error!!!!");
//        }
//
//        // Read from file
//        FileInputStream fileInput;
//        ObjectInputStream objectInput;
//
//        try {
//            fileInput = new FileInputStream(filename);
//            objectInput = new ObjectInputStream(fileInput);
//
//            SomeClassToSerilize sc = (SomeClassToSerilize) objectInput.readObject();
//
//            System.out.println("Deserialized");
//            System.out.println(sc);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        // I. Executor
//        Executor executor = new Invoker();
//
//        try {
//            // executor invokes the submitted task instantly in the invoking thread (main thread)
//            executor.execute(()-> {
//                System.out.println(Thread.currentThread());
//            });
//        } catch (RejectedExecutionException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println(Thread.currentThread());
//
//        // II. ExecutorService
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        executorService.submit(new Task());
//        executorService.shutdown();
//
//        // III. ScheduledExecutorService
//        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//
//        Future<String> future = scheduledExecutorService.schedule(() -> {
//            System.out.println(Thread.currentThread()+" from scheduledExecutorService");
//            return Thread.currentThread()+" from scheduledExecutorService";
//        },5,TimeUnit.SECONDS);
//
//        System.out.println("status? => "+ future.isDone());
//
//        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.schedule(() -> {
//            System.out.println(Thread.currentThread() + "from scheduled future!");
//        }, 8, TimeUnit.SECONDS);
//
//        scheduledExecutorService.shutdown();
//
//        ScheduledExecutorService anotherScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//
//        anotherScheduledExecutorService.scheduleAtFixedRate(() -> {
//            System.out.println("from at fixed rate");
//        }, 1, 10, TimeUnit.SECONDS);
//
//        anotherScheduledExecutorService.scheduleWithFixedDelay(() -> {
//            System.out.println("from with fixed delay");
//            System.out.println("status? => "+ future.isDone());
//
//        }, 1, 10, TimeUnit.SECONDS);
//
//
//        //IV. Future
//        ExecutorService executorServiceIV = Executors.newFixedThreadPool(10);
//
//        Future<String> futureIV = executorServiceIV.submit(() -> {
//
//            Thread.sleep(10000l);
//            return "from the future!!";
//        });
//
//        if (futureIV.isDone() && !futureIV.isCancelled()) {
//            try {
//                String str = futureIV.get(10, TimeUnit.SECONDS);
//                System.out.println(str);
//            } catch (InterruptedException | ExecutionException | TimeoutException exception) {
//                exception.printStackTrace();
//            }
//        }
//
//        // V. Cyclic Barrier
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
//
//            System.out.println("All previous tasks are completed!");
//        });
//
//        Thread t1 = new Thread(new CyclicBarrierTask(cyclicBarrier), "T1");
//        Thread t2 = new Thread(new CyclicBarrierTask(cyclicBarrier), "T2");
//        Thread t3 = new Thread(new CyclicBarrierTask(cyclicBarrier), "T3");
//
//        if (!cyclicBarrier.isBroken()) {
//            t1.start();
//            t2.start();
//            t3.start();
//        }
//
//
//        // VI
//        System.out.println("Available Permit: " + semaphore.availablePermits());
//        System.out.println("Number of threads waiting to acquire: " + semaphore.getQueueLength());
//        semaphore.tryAcquire();
//
//        if (semaphore.tryAcquire()) {
//            try {
//                System.out.println("In Critical Section");
//            } finally {
//                semaphore.release();
//            }
//        }
//
//        // VII
//        CustomThreadFactory threadFactory = new CustomThreadFactory("CustomThreadFactory");
//        for (int i = 0; i < 10; i++) {
//            Thread t = threadFactory.newThread(new Task());
//            t.start();
//        }
//
//        //VIII
//        // Unbounded Queue
//        BlockingQueue<String> nboundedBlockingQueue = new LinkedBlockingDeque<>();
//
//        // Bounded Queue
//        BlockingQueue<String> boundedBlockingQueue = new LinkedBlockingDeque<>(10);
//
//        int BOUND = 10;
//        int N_PRODUCERS = 4;
//        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
//        int poisonPill = Integer.MAX_VALUE;
//        int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;
//        int mod = N_CONSUMERS % N_PRODUCERS;
//
//        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);
//
//        for (int i = 1; i < N_PRODUCERS; i++) {
//            new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer)).start();
//        }
//
//        for (int j = 0; j < N_CONSUMERS; j++) {
//            new Thread(new NumbersConsumer(queue, poisonPill)).start();
//        }
//
//        new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer + mod)).start();

        System.out.println("Terminate!");
    }
}
