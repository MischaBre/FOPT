package synchstacksem;

import java.util.ArrayList;
import java.util.List;

public class SynchStack {
    private final List<Object> data;
    private final Semaphore dataSem;
    private final Semaphore lockSem;

    public SynchStack() {
        this.data = new ArrayList<Object>();
        this.lockSem = new Semaphore(1);
        this.dataSem = new Semaphore(0);
    }

    public void push(Object o) {
        // Object validation
        if (o == null) {
            throw new IllegalArgumentException("Object is null");
        }
        // tries dataLock, adds 1 to dataSemaphore, adds data, returns lock
        lockSem.p();
        data.add(o);
        System.out.println(Thread.currentThread().getName() + ": " + data.toString());
        dataSem.v();
        lockSem.v();
    }

    public Object pop() {
        dataSem.p();
        lockSem.p();
        Object o = data.remove(data.size() - 1);
        System.out.println(Thread.currentThread().getName() + ": Recieving " + o);
        lockSem.v();
        return o;
    }
/*
    public static void main(String[] args) throws InterruptedException {
        SynchStack stack = new SynchStack();
        SynchStackTester s1 = new SynchStackTester("S1", stack, true);
        Thread.sleep(100);
        SynchStackTester r1 = new SynchStackTester("R1", stack, false);
        SynchStackTester r2 = new SynchStackTester("R2", stack, false);
    }
 */
}
