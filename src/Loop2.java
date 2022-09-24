public class Loop2 implements Runnable {

    private String name;
    private int i;

    public Loop2 (String name) {
        this.name = name;
    }

    public void run() {
        i = 0;
        // Thread.currentThread().setName(name);
        while (i < 100) {
            System.out.println(Thread.currentThread().getName() + " (" + i++ + ") ");
        }
    }

    public static void main (String[] args) {

        // Aufgabe 2.3

        Loop2 l1 = new Loop2("Jupp");
        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l1);
        t1.start();
        t2.start();
    }
}
