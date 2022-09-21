package pp.ampel;

public class DeutscheAmpel implements Ampel {
    private int zustand; // 0 = rot, 1 = gruen
    private int wartendeFahrzeuge;

    public DeutscheAmpel() {
        this.zustand = 0;
        this.wartendeFahrzeuge = 0;
    }

    @Override
    public synchronized void schalteRot() {
        this.zustand = 0;
    }

    @Override
    public synchronized void schalteGruen() {
        this.zustand = 1;
        notify();
    }

    @Override
    public synchronized int wartendeFahrzeuge() {
        return wartendeFahrzeuge;
    }

    @Override
    public synchronized void passieren() throws InterruptedException {
        wartendeFahrzeuge++;
        while (zustand == 0) {
            wait();
        }
        wartendeFahrzeuge = 0;
        notify();
    }

    @Override
    public String zeigeZustand() {
        return String.valueOf(zustand);
    }
}
