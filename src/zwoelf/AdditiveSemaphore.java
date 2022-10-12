// Aufgabe 12.2

package zwoelf;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdditiveSemaphore {
    private final ReentrantLock lock;
    private final Condition isEmpty;
    private int counter;

    public AdditiveSemaphore(int counter) {
        lock = new ReentrantLock();
        isEmpty = lock.newCondition();
        this.counter = counter;
    }

    public void acquire(int x) {
        lock.lock();
        try {
            while (counter - x < 0) {
                isEmpty.awaitUninterruptibly();
            }
            counter -= Math.min(x, counter);
        } finally {
            lock.unlock();
        }
    }

    public void release(int x) {
        lock.lock();
        try {
            counter += x;
            isEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
