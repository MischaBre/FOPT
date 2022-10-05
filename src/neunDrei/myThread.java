package neunDrei;

import java.util.concurrent.Semaphore;

public class myThread extends Thread {
    private String name;
    private Semaphore sem1;
    private Semaphore sem2;

    public myThread(String name, Semaphore sem1, Semaphore sem2) {
        this.name = name;
        this.sem1 = sem1;
        this.sem2 = sem2;
    }

    private void printMsg() {
        System.out.println("Ich bin der " + name);
    }

    public void run() {
        while( true) {
            try {
                sem1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printMsg();
            sem2.release();
        }
    }
}
