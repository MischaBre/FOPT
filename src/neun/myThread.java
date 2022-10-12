// Aufgabe 9.3

package neun;

import java.util.concurrent.Semaphore;

public class myThread extends Thread {
    private final String name;
    private final Semaphore sem1;
    private final Semaphore sem2;

    public myThread(String name, Semaphore sem1, Semaphore sem2) {
        this.name = name;
        this.sem1 = sem1;
        this.sem2 = sem2;
        start();
    }

    private void printMsg() {
        System.out.println("Ich bin der " + name);
    }

    public void run() {
        while(true) {
            try {
                sem1.acquire();
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printMsg();
            sem2.release();
        }
    }
}
