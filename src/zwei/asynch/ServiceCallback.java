package zwei.asynch;

public class ServiceCallback implements Runnable {
    private final boolean[] array;
    private final int start;
    private final int end;
    private final ResultListener h;

    public ServiceCallback(boolean[] array, int start, int end, ResultListener listener) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.h = listener;
    }

    @Override
    public void run() {
        int result = 0;
        for (int i = start; i <= end; i++) {
            if (array[i]) {
                if (result % 1000000 == 0) System.out.println(Thread.currentThread().getName() + " - " + result);
                result++;
            }
        }
        h.putResult(result);
    }
}
