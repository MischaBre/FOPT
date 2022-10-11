/* FOPT ASB Aufgabe 1
   2022-09-22
   Michael Gemsa
 */

package ampel;

public interface Ampel {

    void schalteRot();

    void schalteGruen();

    void passieren() throws InterruptedException;

    int wartendeFahrzeuge();

    String zeigeZustand();
}
