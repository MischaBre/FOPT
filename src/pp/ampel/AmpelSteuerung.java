/* FOPT ASB Aufgabe 1
   2022-09-22
   Michael Gemsa
 */

package pp.ampel;

public class AmpelSteuerung extends Thread {
    private static final int AMPELANZAHL = 3;
    private static final int AUTOZEIT = 1000;
    private static final int AMPELZEIT = 5000;
    private final Ampel[] ampeln;

    public AmpelSteuerung(Ampel[] ampeln, String name) {
        super(name);
        this.ampeln = ampeln;
        start();
    }

    @Override
    public void run() {
        StringBuilder ampelZustaende = new StringBuilder();
        while (true) {
            for (Ampel a : ampeln) {
                synchronized (a) {
                    if (Math.random() > 0.5) {
                        a.schalteGruen();
                    } else {
                        a.schalteRot();
                    }
                }
            }
            ampelZustaende.delete(0, ampelZustaende.length());
            for (Ampel a : ampeln) {
                ampelZustaende.append(a.zeigeZustand()).append("___");
            }
            System.out.println(ampelZustaende);
            try {
                sleep(AMPELZEIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Ampel[] a = new Ampel[AMPELANZAHL];
        for (int i = 0; i < AMPELANZAHL; i++) {
            // a[i] = new ItalienischeAmpel();
            a[i] = new DeutscheAmpel();
        }
        AmpelSteuerung as = new AmpelSteuerung(a, "Ampelsteuerung1");
        while (true) {
            try {
                sleep(AUTOZEIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Auto(a);
        }
    }
}
