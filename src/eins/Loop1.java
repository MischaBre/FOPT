package eins;

public class Loop1 implements Runnable {
    private final String myName;

    public Loop1 (String name) {
        myName = name;
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(myName + " (" + i + ") ");
        }
    }

    public static void main (String[] args) {

        // Aufgabe 2.2

        Loop1 l1 = new Loop1("Thread 1");
        Loop1 l2 = new Loop1("Thread 2");
        Loop1 l3 = new Loop1("Thread 3");

        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l2);
        Thread t3 = new Thread(l3);

        t1.start();
        t2.start();
        t3.start();
    }
}
