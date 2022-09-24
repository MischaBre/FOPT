package zweiZwanzig.TaskNodeExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskCountService implements Task <Integer> {
    private boolean[] array;
    private int minLength;

    public TaskCountService (boolean[] array, int minLength) {
        this.array = array;
        this.minLength = minLength;
    }

    @Override
    public boolean isDivisible() {
        return array.length > minLength;
    }

    @Override
    public List<Task<Integer>> split() {
        List<Task<Integer>> splitList = new ArrayList<>();
        System.out.println("Splitting " + array.length + " at " + array.length/2);
        splitList.add(new TaskCountService(Arrays.copyOfRange(array, 0, array.length / 2), minLength));
        splitList.add(new TaskCountService(Arrays.copyOfRange(array, array.length / 2, array.length), minLength));
        return splitList;
    }

    @Override
    public Integer combine(List<Integer> results) {
        int result = 0;
        for (Integer r : results) {
            result += r;
        }
        System.out.println("Combining " + result);
        return result;
    }

    @Override
    public Integer execute() {
        int result = 0;
        for (boolean a : array) {
            if (a) {
                result++;
            }
        }
        return result;
    }
}
