package praktikum.tag3.rmimultiplikation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Rechnen extends Remote {
    int multiplikation(int x, int y) throws RemoteException;
}
