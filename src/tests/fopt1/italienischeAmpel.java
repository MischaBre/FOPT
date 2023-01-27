package tests.fopt1;

public class italienischeAmpel implements Ampel {
    private boolean istGruen;
    private int wartendeFahrzeuge;

    public italienischeAmpel() {
    }

    synchronized public void schalteRot() {
        istGruen = false;
    }

    synchronized public void schalteGruen() {
        istGruen = true;
        notifyAll();
    }

    synchronized public void passieren() {
        wartendeFahrzeuge++;
        while (!istGruen) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        wartendeFahrzeuge--;
    }

    public int wartendeFahrzeuge() {
        return wartendeFahrzeuge;
    }
}
