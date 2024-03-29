package praktikum.tag3.rmiMesswerte;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MesswertClientImpl extends UnicastRemoteObject implements MesswertClient {
    private final String name;
    public int min, max;
    private MesswertServer server;

    public MesswertClientImpl(String name, int min, int max) throws RemoteException {
        this.name = name;
        this.min = min;
        this.max = max;
        try {
            server = (MesswertServer) Naming.lookup("rmi://localhost:1111/MesswerteServer");
            server.addClient(this);
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws RemoteException {
        server.removeClient(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void printWert(int wert) throws RemoteException {
        System.out.println(name + ": " + wert);
    }
    @Override
    public int getMax() {
        return max;
    }

    @Override
    public int getMin() {
        return min;
    }
}
