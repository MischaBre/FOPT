package neunEins;

public class BinSemaphore {
    private boolean locked;

    public BinSemaphore(boolean locked) {
        this.locked = locked;
    }

    public BinSemaphore() {
    }

    public synchronized void lock() {
        while (locked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        locked = true;
    }

    public synchronized void unlock() {
        if (locked) {
            locked = false;
            notify();
        }
    }
}
