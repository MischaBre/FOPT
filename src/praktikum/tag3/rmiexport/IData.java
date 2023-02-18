package praktikum.tag3.rmiexport;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IData extends Remote {
    int getNumber() throws RemoteException;
    void setNumber(int number) throws RemoteException;
}
