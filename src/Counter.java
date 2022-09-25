public class Counter extends Thread {
    private final CounterManager counterManager;
    private final double rando;
    private static final int WAIT = 10000;

    public Counter(String name, CounterManager cm, double rnd) {
        super(name);
        this.counterManager = cm;
        this.rando = rnd;
        start();
    }

    private void sleepRnd() {
        try {
            sleep((int) (WAIT * Math.random()));
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void run() {
        while (true) {

            sleepRnd();
            synchronized(counterManager) {
                if (rando > 0.5) {
                    try {
                        counterManager.increment();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    System.out.println(System.nanoTime() + " - " + getName() + ": plus - " + counterManager.getCount());
                } else {
                    try {
                        counterManager.decrement();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    System.out.println(System.nanoTime() + " - " + getName() + ": minus - " + counterManager.getCount());
                }
            }
            sleepRnd();
            synchronized(counterManager) {
                if (rando < 0.5) {
                    try {
                        counterManager.increment();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    System.out.println(System.nanoTime() + " - " + getName() + ": plus - " + counterManager.getCount());
                } else {
                    try {
                        counterManager.decrement();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    System.out.println(System.nanoTime() + " - " + getName() + ": minus - " + counterManager.getCount());
                }
            }
        }
    }
}
