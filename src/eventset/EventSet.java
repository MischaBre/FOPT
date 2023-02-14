package eventset;

public class EventSet {
    private boolean[] data;

    public EventSet(int length) {
        if (length < 0) throw new IllegalArgumentException("nicht < 0");
        data = new boolean[length];
    }

    public synchronized void set(int i, boolean value) {
        if (i < 0 || i >= data.length) {
            throw new IllegalArgumentException("falsches i");
        }
        data[i] = value;
        notifyAll();
    }

    private boolean readValues(boolean readAll) {
        for (int i = 0; i < data.length; i++) {
            // wenn waitOR: das erste true -> return true
            if (!readAll && data[i]) return true;
            // wenn waitAND: das erste false -> return false
            if (readAll && !data[i]) return false;
        }
        // waitAND: alle elemente in data sind wie readAll -> return
        return readAll;
    }

    public synchronized void waitOR() {
        while (!readValues(false)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void waitAND() {
        while (!readValues(true)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
