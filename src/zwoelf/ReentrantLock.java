package zwoelf;

public class ReentrantLock {

    private int counter;
    private Thread currentThread;

    public ReentrantLock() {
        counter = 0;
    }

    public synchronized void lock() {
        while (counter > 0 && Thread.currentThread() != currentThread) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (Thread.currentThread() != currentThread) {
            currentThread = Thread.currentThread();
        }
        counter++;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() != currentThread) {
            throw new IllegalAccessError("Falscher Thread");
        }
        counter--;
        if (counter == 0) {
            currentThread = null;
            notify();
        }
    }
}
