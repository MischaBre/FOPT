package zehnZwei;

import java.util.concurrent.Semaphore;

public class BufferSem {

    private int tail;
    private int head;
    private int[] data;
    private Semaphore mutexSem;
    private Semaphore itemSem;
    private Semaphore placesSem;

    public BufferSem(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("zu kleine Zahl");
        }
        head = 0;
        tail = 0;
        data = new int[n];
        mutexSem = new Semaphore(1);
        itemSem = new Semaphore(0);
        placesSem = new Semaphore(n);
    }

    public void put(int num) throws InterruptedException {
        placesSem.acquire();
        mutexSem.acquire();
        data[tail++] = num;
        if (tail == data.length) {
            tail = 0;
        }
        mutexSem.release();
        itemSem.release();
    }

    public int get() throws InterruptedException {
        itemSem.acquire();
        mutexSem.acquire();
        int result = data[head++];
        if (head == data.length) {
            head = 0;
        }
        mutexSem.release();
        placesSem.release();
        return result;
    }
}
