/* FOPT ASB Aufgabe 1
   2022-09-22
   Michael Gemsa
 */

package pp.ampel;

public class ItalienischeAmpel implements Ampel {
    private int zustand; // 0 = rot, 1 = gruen
    private int wartendeFahrzeuge;

    public ItalienischeAmpel() {
        this.zustand = 0;
        this.wartendeFahrzeuge = 0;
    }

    @Override
    public void schalteRot() {
        this.zustand = 0;
    }

    @Override
    public void schalteGruen() {
        this.zustand = 1;
        notifyAll();
    }

    @Override
    public int wartendeFahrzeuge() {
        return wartendeFahrzeuge;
    }

    @Override
    public synchronized void passieren() throws InterruptedException {
        while (zustand == 0) {
            wartendeFahrzeuge++;
            wait();
        }
        if (wartendeFahrzeuge > 0) {
            wartendeFahrzeuge = 0;
        }
    }

    @Override
    public String zeigeZustand() {
        return String.valueOf(zustand);
    }
}
