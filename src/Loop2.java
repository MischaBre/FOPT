public class Loop2 {

    public static void hauRaus(String myName) {
        for (int i = 1; i <= 1000; i++) {
            System.out.println(myName + " (" + i + ") ");
        }
    }

    public static void main (String[] args) {

        // Aufgabe 2.3

        Thread t1 = new Thread(() -> hauRaus("Thread 1"));
        Thread t2 = new Thread(() -> hauRaus("Thread 2"));
        Thread t3 = new Thread(() -> hauRaus("Thread 3"));

        t1.start();
        t2.start();
        t3.start();
    }
}
