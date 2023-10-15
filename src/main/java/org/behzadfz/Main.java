package org.behzadfz;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

import org.behzadfz.concurrency.Invoker;

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

        System.out.println("Terminate!");
    }
}
