package synchstacksem;

public class SynchStackTester extends Thread {
    private final boolean isSender;
    private final SynchStackRaw stack;

    public SynchStackTester(String name, SynchStackRaw stack, boolean isSender) {
        super(name);
        this.stack = stack;
        this.isSender = isSender;
        start();
    }

    @Override
    public void run() {
        while (true) {
            if (isSender) {
                int i = (int) (Math.random() * 10);
                stack.push(i);
            } else {
                Object result = stack.pop();
            }
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
