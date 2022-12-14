package sockets.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Counter extends Remote {
    public int reset() throws RemoteException;
    public int increment() throws RemoteException;
}
