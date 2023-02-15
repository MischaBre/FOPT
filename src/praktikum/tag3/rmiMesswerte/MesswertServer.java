package praktikum.tag3.rmiMesswerte;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MesswertServer extends Remote {
    void setMesswert(int messwert) throws RemoteException;
    int getMesswert() throws RemoteException;
    List<MesswertClient> getClients() throws RemoteException;
    void addClient(MesswertClient client) throws RemoteException;
    void removeClient(MesswertClient client) throws RemoteException;

}
