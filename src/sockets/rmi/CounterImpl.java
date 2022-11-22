package sockets.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CounterImpl extends UnicastRemoteObject {
    private int counter;

    public CounterImpl() throws RemoteException {
    }

    public synchronized int reset() throws RemoteException {
        System.out.println("reset");
        counter = 0;
        return counter;
    }

    public synchronized int increment() throws RemoteException {
        counter++;
        return counter;
    }
}
