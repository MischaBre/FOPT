package counter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedCounterLock {
    private int counter, min, max;
    private Lock lock;
    private Condition conditionUp, conditionDown;

    public BoundedCounterLock(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Fehler: min > max");
        }
        this.min = min;
        this.counter = min;
        this.max = max;
        lock = new ReentrantLock();
        conditionUp = lock.newCondition();
        conditionDown = lock.newCondition();
    }

    public synchronized int get() {
        return counter;
    }

    public void up() {
        lock.lock();
        try {
            while(counter == max) {
                conditionUp.awaitUninterruptibly();
            }
            System.out.println(Thread.currentThread().getName() + ": " + ++counter);
            conditionUp.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void down() {
        lock.lock();
        try {
            while (counter == min) {
                conditionDown.awaitUninterruptibly();
            }
            System.out.println(Thread.currentThread().getName() + ": " + --counter);
            conditionDown.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        BoundedCounterLock bc = new BoundedCounterLock(10, 100);

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(40,100,0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        List<Runnable> threadArray = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            tpe.execute(()-> {
                for (int j = 0; j < 50; j++) {
                    bc.up();
                }
            });
            tpe.execute(()-> {
                for (int j = 0; j < 50; j++) {
                    bc.down();
                }
            });
        }
    }
}
