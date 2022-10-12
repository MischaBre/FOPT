package zwei.TaskNodeExecutor;

import java.util.List;

public interface Task <T> {
    boolean isDivisible();
    List<Task<T>> split();
    T combine(List<T> results);
    T execute();
}
