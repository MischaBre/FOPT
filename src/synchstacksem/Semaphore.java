package synchstacksem;

public class Semaphore {
    private int value;

    public Semaphore(int init) {
        if (init < 0) {
            throw new IllegalArgumentException("Parameter < 0");
        }
        this.value = init;
    }

    public synchronized void p() {
        while (value - 1 < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        value -= 1;
    }

    public synchronized void v() {
        value += 1;
        notifyAll(); // nicht notify()
    }

}
