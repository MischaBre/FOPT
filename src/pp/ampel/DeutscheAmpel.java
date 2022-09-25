/* FOPT ASB Aufgabe 1
   2022-09-22
   Michael Gemsa
 */

package pp.ampel;

public class DeutscheAmpel implements Ampel {
    private int zustand; // 0 = rot, 1 = gruen
    private int warteNummer;
    private int fahrNummer;
    private int wartendeFahrzeuge;

    public DeutscheAmpel() {
        this.zustand = 0;
        this.wartendeFahrzeuge = 0;
        this.warteNummer = 0;
        this.fahrNummer = 0;
    }

    @Override
    public synchronized void schalteRot() {
        this.zustand = 0;
    }

    @Override
    public synchronized void schalteGruen() {
        this.zustand = 1;
        notifyAll();
    }

    @Override
    public synchronized int wartendeFahrzeuge() {
        return wartendeFahrzeuge;
    }

    @Override
    public synchronized void passieren() throws InterruptedException {
        int myWarteNummer = warteNummer++;
        wartendeFahrzeuge++;
        while (myWarteNummer != fahrNummer || zustand == 0) {
            wait();
        }
        wartendeFahrzeuge = 0;
        fahrNummer++;
        notifyAll();
    }

    @Override
    public String zeigeZustand() {
        return String.valueOf(zustand);
    }
}
