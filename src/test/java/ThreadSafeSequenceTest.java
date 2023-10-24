import org.behzadfz.advanced.UnsafeSequence;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadSafeSequenceTest {

    @Test
    public void testSequenceInMultiThread() throws InterruptedException {
        UnsafeSequence unsafeSequence = new UnsafeSequence();

        List<Integer> list1 = new ArrayList<>();

        Runnable task1 = () -> {
            for (int i = 0;i < 50; i++) {
                list1.add(unsafeSequence.getNext());
                System.out.println("Task #1 is running" + i );
            }
        };

        Runnable task2 = () -> {
            for (int i = 0;i < 50; i++) {
                list1.add(unsafeSequence.getNext());
                System.out.println("Task #2 is running" + i);
            }
        };

        Runnable task3 = () -> {
            for (int i = 0;i < 50; i++) {
                list1.add(unsafeSequence.getNext());
                System.out.println("Task #3 is running" + i);
            }
        };

        Runnable task4 = () -> {
            for (int i = 0;i < 50; i++) {
                list1.add(unsafeSequence.getNext());
                System.out.println("Task #4 is running" + i);
            }
        };

        Runnable task5 = () -> {
            for (int i = 0;i < 50; i++) {
                list1.add(unsafeSequence.getNext());
                System.out.println("Task #5 is running" + i);
            }
        };

        Runnable task6 = () -> {
            for (int i = 0;i < 50; i++) {
                list1.add(unsafeSequence.getNext());
                System.out.println("Task #6 is running" + i);
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);
        Thread thread4 = new Thread(task4);
        Thread thread5 = new Thread(task5);
        Thread thread6 = new Thread(task6);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();

        list1.sort(Integer::compareTo);
    }
}
