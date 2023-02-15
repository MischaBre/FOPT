package praktikum.tag3.rmiMesswerte;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MesswertClient extends Remote {
    String getName() throws RemoteException;
    void printWert(int wert) throws RemoteException;
    int getMin() throws RemoteException;
    int getMax() throws RemoteException;
}
