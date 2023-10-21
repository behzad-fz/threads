package org.behzadfz.concurrency.deleyqueue;

import com.google.common.primitives.Ints;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayObject implements Delayed {
    private String data;
    private long startTime;

    public DelayObject(String data, long delayInMilliseconds) {
        this.data = data;
        this.startTime = System.currentTimeMillis() + delayInMilliseconds;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();

        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        /*
            it ensures that if the value being cast to an int is larger than the maximum representable
            value for an int, it will "saturate" the result to the maximum int value
            (i.e., Integer.MAX_VALUE), rather than throwing an exception or wrapping around to a
            negative value
        */
        return Ints.saturatedCast(
                this.startTime - ((DelayObject) o).startTime);
    }
}
