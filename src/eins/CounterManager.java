package eins;

public class CounterManager {
    private int count;
    static int MAXCOUNTER = 4;

    public CounterManager() {
        count = 10;
    }

    public static void main(String[] args) {
        CounterManager cm = new CounterManager();
        System.out.println(cm.getCount());
        Counter[] couArr = new Counter[MAXCOUNTER];
        for (int i = 0; i < MAXCOUNTER; i++) {
            couArr[i] = new Counter(String.valueOf(i), cm, Math.random());
        }
    }

    public int getCount() {
        return count;
    }

    public synchronized void increment() throws InterruptedException {
        while (count == 10) {
            System.out.println("wait");
            wait();
        }
        count++;
        notify();
    }

    public void decrement() throws InterruptedException {
        while (count == 0) {
            System.out.println("wait");
            wait();
        }
        count--;
        notify();
    }
}
