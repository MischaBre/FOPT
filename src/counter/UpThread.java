package counter;

public class UpThread extends Thread {
    private BoundedCounter bc;
    private int n;

    public UpThread(BoundedCounter bc, int n) {
        this.bc = bc;
        this.n = n;
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            bc.up();
        }
    }
}
