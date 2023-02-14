package synchstacksem;

import java.util.ArrayList;
import java.util.List;

public class SynchStackRaw {
    private final List<Object> data;

    public SynchStackRaw() {
        this.data = new ArrayList<Object>();
    }

    public synchronized void push(Object o) {
        // Object validation
        if (o == null) {
            throw new IllegalArgumentException("Object is null");
        }
        data.add(o);
        System.out.println(Thread.currentThread().getName() + ": " + data.toString());
        notify();
    }

    public synchronized Object pop() {
        while(data.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object o = data.remove(data.size() - 1);
        System.out.println(Thread.currentThread().getName() + ": Receiving " + o.toString());
        return o;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchStackRaw stack = new SynchStackRaw();
        SynchStackTester s1 = new SynchStackTester("S1", stack, true);
        Thread.sleep(100);
        SynchStackTester r1 = new SynchStackTester("R1", stack, false);
        SynchStackTester r2 = new SynchStackTester("R2", stack, false);
    }

}
