package org.behzadfz.concurrency;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {

    private int threadId;
    private String name;

    public CustomThreadFactory(String name) {
        threadId = 1;
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "Thread_" + threadId);
        System.out.println("creating new thread with Id:"+ threadId + "and name"+ t.getName());
        threadId++;
        return t;
    }
}
