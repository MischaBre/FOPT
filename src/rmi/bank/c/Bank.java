package rmi.bank.c;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote, Serializable {
    public Account getAccount(int num) throws RemoteException;
}
