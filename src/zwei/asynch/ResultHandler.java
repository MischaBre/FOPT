package zwei.asynch;

public class ResultHandler implements ResultListener {

    private int result;
    private int numberOfResults;
    private final int expectedNumberOfResults;

    public ResultHandler(int expectedNumberOfResults) {
        this.expectedNumberOfResults = expectedNumberOfResults;
    }

    public synchronized  void putResult(int r) {
        result += r;
        numberOfResults++;
        if (numberOfResults == expectedNumberOfResults) {
            System.out.println("Ergebnis: " + result);
        } else {
            System.out.println(Thread.currentThread().getName() + ", " + r + ", " + result + ", " + numberOfResults);
        }
    }
}
