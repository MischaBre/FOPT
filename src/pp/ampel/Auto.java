/* FOPT ASB Aufgabe 1
   2022-09-22
   Michael Gemsa
 */

package pp.ampel;

public class Auto extends Thread {
    private final Ampel[] ampeln;

    public Auto(Ampel[] ampeln) {
        super();
        this.ampeln = ampeln;
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < ampeln.length; i++) {
            synchronized (ampeln[i]) {
                try {
                    System.out.println(getName() + " wartet an Ampel " + i);
                    ampeln[i].passieren();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName() + " hat Ampel " + i + " passiert");
            }
        }
        System.out.println(getName() + " ist weg");
    }
}
