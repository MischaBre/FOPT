package tests.fopt1;

import java.util.List;
import java.util.Stack;

public class DeutscheAmpel implements Ampel {

    public boolean zustand;
    private List<Thread> warteschlange = new Stack<>();

    public DeutscheAmpel() {
    }

    synchronized public void schalteRot() {
        zustand = false;
    }

    synchronized public void schalteGruen() {
        zustand = true;
        notifyAll();
    }

    synchronized public void passieren() {
        warteschlange.add(Thread.currentThread());
        while (!zustand && Thread.currentThread() != warteschlange.get(0)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        warteschlange.remove(0);
        notifyAll();
    }

    public int wartendeFahrzeuge() {
        return warteschlange.size();
    }
}
