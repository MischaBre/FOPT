package zweiZwanzig.TaskNodeExecutor;

import java.util.ArrayList;
import java.util.List;

public class TaskNodeExecutor <T> extends Thread {
    private final Task<T> task;
    private T result;

    public TaskNodeExecutor(Task<T> task) {
        this.task = task;
    }

    public void run() {
        if (task.isDivisible()) {
            List<Task<T>> subtasks = task.split();
            List<TaskNodeExecutor<T>> threads = new ArrayList<>();
            for (Task<T> subtask : subtasks) {
                TaskNodeExecutor<T> thread = new TaskNodeExecutor<>(subtask);
                threads.add(thread);
                thread.start();
            }
            List<T> subresults = new ArrayList<>();
            for (TaskNodeExecutor<T> thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subresults.add(thread.getResult());
            }
            result = task.combine(subresults);
        } else {            // !task.isDivisible()
            result = task.execute();
        }
    }

    public T getResult() {
        return result;
    }

    public static <T> T executeAll(Task<T> task) {
        TaskNodeExecutor<T> root = new TaskNodeExecutor<>(task);
        root.run();
        return root.getResult();
    }
}
