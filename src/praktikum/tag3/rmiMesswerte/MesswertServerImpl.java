package praktikum.tag3.rmiMesswerte;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MesswertServerImpl extends UnicastRemoteObject implements MesswertServer {
    private final Thread worker;
    private Integer messwert;
    private final List<MesswertClient> clientList;

    public MesswertServerImpl() throws RemoteException {
        clientList = new ArrayList<>();
        worker = new Worker(this);
    }

    public synchronized List<MesswertClient> getClients() throws RemoteException {
        return clientList;
    }
    public synchronized void addClient(MesswertClient client) throws RemoteException {
        clientList.add(client);
    }

    public void setMesswert(int messwert) throws RemoteException {
        this.messwert = messwert;
    }
    public int getMesswert() throws RemoteException {
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
