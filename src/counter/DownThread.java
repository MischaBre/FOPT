package counter;

public class DownThread extends Thread {
    private BoundedCounter bc;
    private int n;

    public DownThread(BoundedCounter bc, int n) {
        this.bc = bc;
        this.n = n;
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            bc.down();
        }
    }
}
