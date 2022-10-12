package zwei.TaskNodeExecutor;

public class TaskNodeCounting {
    private static final int ARRAY_SIZE = 500_000_000;
    private static final int MAX_LEVEL = 12;

    public static void main(String[] args) {
        boolean[] array = new boolean[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i = i + 2) {
            array[i] = true;
        }
        for (int i = 1; i < MAX_LEVEL; i++) {
            long startTime = System.nanoTime();
            System.out.println("Ergebnis :" + TaskNodeExecutor.executeAll(new TaskCountService(array, 0, ARRAY_SIZE, ARRAY_SIZE / i, 1)));
            System.out.println("Ebenen: " + i + " Zeit verstrichen: " + (System.nanoTime() - startTime) / 1000_000 + "ms");
        }
    }
}
