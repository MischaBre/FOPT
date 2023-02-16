package praktikum.tag3.rmiMesswerte;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MesswertServerImpl extends UnicastRemoteObject implements MesswertServer {
    private final Thread worker, sender;
    private int messwert;
    private final List<MesswertClient> clientList;

    public MesswertServerImpl() throws RemoteException {
        clientList = new ArrayList<>();
        worker = new Worker(this);
        sender = new Sender(this);
    }

    public synchronized List<MesswertClient> getClients() throws RemoteException {
        return clientList;
    }
    public synchronized void addClient(MesswertClient client) throws RemoteException {
        clientList.add(client);
    }

    @Override
    public synchronized void removeClient(MesswertClient client) throws RemoteException {
        clientList.remove(client);
    }

    public synchronized void setMesswert(int messwert) throws RemoteException {
        // Messwert setzen und den Sender -> notify
        this.messwert = messwert;
        notify();
    }

    public synchronized int getMesswert(int lastMesswert) throws RemoteException {
        // Wenn Messwert schon abgeholt, dann warten
        while(lastMesswert == this.messwert) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        // Messwert ist neu, dann return
        return messwert;
    }

    public static void main(String[] args) {
        try {
            MesswertServerImpl server = new MesswertServerImpl();
            Registry registry = LocateRegistry.createRegistry(1111);
            registry.rebind("MesswerteServer", server);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
