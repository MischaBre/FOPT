package tests.fopt1;

import javafx.scene.effect.InnerShadow;

public class EventSet {

    private boolean[] set;

    public EventSet(int laenge) {
        if (laenge < 1) {
            throw new IllegalArgumentException("blub");
        }
        set = new boolean[laenge];
    }

    public synchronized void set(int i, boolean value) {
        if (i < 1 || i > set.length -1) {
            throw new IllegalArgumentException("möp");
        }
        set[i] = value;
        notifyAll();
    }

    public synchronized void waitAND() {
        while (!allTrue()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void waitOR() {
        while (!oneTrue()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean allTrue() {
        for (boolean s : set) {
            if (!s) {
                return false;
            }
        }
        return true;
    }

    private boolean oneTrue() {
        for (boolean s : set) {
            if (s) {
                return true;
            }
        }
        return false;
    }

}
