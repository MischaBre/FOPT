package praktikum.tag3.rmiexport;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDemo extends Remote {

    void increment(IData data) throws RemoteException;
}
