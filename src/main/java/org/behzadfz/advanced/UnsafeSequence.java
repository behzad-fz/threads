package org.behzadfz.advanced;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequence {
    private int value;

    public int getNext() {
        return value++;
    }
}
