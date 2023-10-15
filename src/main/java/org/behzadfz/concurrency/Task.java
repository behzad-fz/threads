package org.behzadfz.concurrency;

public class Task implements Runnable{

    @Override
    public void run() {
        System.out.println("from taks, thread:"+ Thread.currentThread());
    }
}