package org.behzadfz.concurrency;

import java.util.concurrent.Executor;

public class Invoker implements Executor {

    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
