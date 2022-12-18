package rmi.central;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    public Data open() throws RemoteException;

    public Data get() throws RemoteException;

    public Data close() throws RemoteException;

    public boolean isOpen() throws RemoteException;
}
