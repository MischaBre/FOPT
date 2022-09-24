package zweiZwanzig.TaskNodeExecutor;

public class TaskNodeCounting {
    private static final int ARRAY_SIZE = 500_000_000;
    private static final int MIN_LENGTH = ARRAY_SIZE / 8;

    public static void main(String[] args) {
        boolean[] array = new boolean[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i = i + 2) {
            array[i] = true;
        }

        System.out.println("Ergebnis :" + TaskNodeExecutor.executeAll(new TaskCountService(array, MIN_LENGTH)));
    }
}
