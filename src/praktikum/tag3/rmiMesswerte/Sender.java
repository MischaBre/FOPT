package praktikum.tag3.rmiMesswerte;

import java.rmi.RemoteException;
import java.util.List;

public class Sender extends Thread {
    int lastMesswert;
    MesswertServer server;

    public Sender(MesswertServer server) {
        this.server = server;
        start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                // versucht Messwerte zu holen und an alle registrierten Clients zu schicken
                int messwert = server.getMesswert(lastMesswert);
                lastMesswert = messwert;
                List<MesswertClient> clients = server.getClients();
                int counter = 0;
                for (MesswertClient cl : clients) {
                    if (messwert >= cl.getMin() && messwert <= cl.getMax()) {
                        counter++;
                        cl.printWert(messwert);
                    }
                }
                System.out.println("received " + messwert + " and sent to: " + counter + " clients");
            } catch (RemoteException e) {

            }
        }
    }
}
