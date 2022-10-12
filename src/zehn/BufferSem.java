/* Aufgabe 10.2
   Erzeuger-Verbraucher Problem aus Lehrheft Listing 3.6
   mit Semaphoren anstatt synchronized, wait, notify.
 */

package zehn;

import java.util.concurrent.Semaphore;

public class BufferSem {

    private int tail;
    private int head;
    private final int[] data;
    private final Semaphore mutexSem;
    private final Semaphore itemSem;
    private final Semaphore placesSem;

    public BufferSem(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("zu kleine Zahl");
        }
        head = 0;
        tail = 0;
        data = new int[n];
        mutexSem = new Semaphore(1); // Semaphore für Zugriff auf Buffer
        itemSem = new Semaphore(0); // Semaphore für Auslesen von Daten (geht von 0-n)
        placesSem = new Semaphore(n); // Semaphore für Ablegen von Daten (geht von n-0)
    }

    public void put(int num) throws InterruptedException {
        placesSem.acquire(); // prüfen, ob Daten abgelegt werden können
        mutexSem.acquire(); // Zugriff auf Daten erhalten
        data[tail++] = num; // Daten ablegen
        if (tail == data.length) {
            tail = 0;
        }
        mutexSem.release(); // Zugriff auf Daten zurückgeben
        itemSem.release(); // Signalisieren, dass Daten gelesen werden können
    }

    public int get() throws InterruptedException {
        itemSem.acquire(); // prüfen, ob Daten gelesen werden können
        mutexSem.acquire(); // Zugriff auf Daten erhalten
        int result = data[head++]; // Daten auslesen
        if (head == data.length) {
            head = 0;
        }
        mutexSem.release(); // Zugriff auf Daten zurückgeben
        placesSem.release(); // Signalisieren, dass Daten abgelegt werden können
        return result;
    }
}
