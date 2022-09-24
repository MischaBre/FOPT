package zweiSiebzehn;

public class StopThreadByInterrupt extends Thread {
    public StopThreadByInterrupt() {
        start();
    }

    public void run() {
        int i = 0;
        try {
            while (!isInterrupted()) {
                i++;
                System.out.println("blub" + i);
                sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fettig!");
    }

    public static void main (String[] args) {
        StopThreadByInterrupt st = new StopThreadByInterrupt();
        try {
            Thread.sleep(9100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        st.interrupt();
    }
}
