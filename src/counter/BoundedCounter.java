package counter;

public class BoundedCounter {
    private int counter, min, max;

    public BoundedCounter(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Fehler: min > max");
        }
        this.min = min;
        this.counter = min;
        this.max = max;
    }

    public synchronized int get() {
        return counter;
    }

    public synchronized void up() {
        while (counter == max) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter++;
        notifyAll();
    }

    public synchronized void down() {
        while (counter == min) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter--;
        notifyAll();
    }

    public static void main(String[] args) {
        BoundedCounter bc = new BoundedCounter(10, 100);
        for (int i = 0; i < 3; i++) {
            UpThread upThread = new UpThread(bc, 10);
            DownThread downThread = new DownThread(bc, 10);
        }
    }
}
