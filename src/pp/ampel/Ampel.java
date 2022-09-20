/* FOPT ASB Aufgabe 1
   2022-09-22
   Michael Gemsa
 */

package pp.ampel;

public interface Ampel {

    public void schalteRot();

    public void schalteGruen();

    public void passieren() throws InterruptedException;

    public int wartendeFahrzeuge();

    public String zeigeZustand();
}
