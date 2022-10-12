package zwei.asynch;

public class AsynchRequestCallback {
    private static final int ARRAY_SIZE = 0x6FFFFFFF;
    private static final int NUM_SERVER = 4;
    public static void main(String[] args) {
        boolean[] array = new boolean[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i = i + 2) {
            array[i] = true;
        }

        ResultHandler handler = new ResultHandler(NUM_SERVER);

        int start = 0;
        int end;
        int howMany = ARRAY_SIZE / NUM_SERVER;

        for (int i = 0; i < NUM_SERVER; i++) {
            if (i < NUM_SERVER - 1) {
                end = start + howMany - 1;
            } else {
                end = ARRAY_SIZE - 1;
            }
            ServiceCallback service = new ServiceCallback(array, start, end, handler);
            Thread t = new Thread(service);
            t.start();
            start = end + 1;
        }
    }
}
