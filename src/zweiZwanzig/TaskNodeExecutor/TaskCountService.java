package zweiZwanzig.TaskNodeExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskCountService implements Task <Integer> {
    private boolean[] array;
    private int startIndex;
    private int endIndex;
    private int minLength;
    private int recursionLevel;

    public TaskCountService (boolean[] array, int startIndex, int endIndex, int minLength, int recursionLevel) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.minLength = minLength;
        this.recursionLevel = recursionLevel;
    }

    @Override
    public boolean isDivisible() {
        return endIndex - startIndex > minLength;
    }

    @Override
    public List<Task<Integer>> split() {
        List<Task<Integer>> splitList = new ArrayList<>();
        int splitIndex = (endIndex + startIndex) / 2;
        /* System.out.println(Thread.currentThread().getName() +
                ": Splitting " + startIndex +
                "-" + endIndex +
                " at " + splitIndex +
                " on level " + recursionLevel); */
        splitList.add(new TaskCountService(array, startIndex, splitIndex, minLength, recursionLevel + 1));
        splitList.add(new TaskCountService(array, splitIndex, endIndex, minLength, recursionLevel + 1));
        return splitList;
    }

    @Override
    public Integer combine(List<Integer> results) {
        int result = 0;
        for (Integer r : results) {
            result += r;
        }
        // System.out.println("Combining " + result);
        return result;
    }

    @Override
    public Integer execute() {
        int result = 0;
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i]) {
                result++;
            }
        }
        return result;
    }
}
