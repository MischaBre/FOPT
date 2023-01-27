package rmi.bank.b;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Account extends Remote, Serializable {
    public double readBalance() throws RemoteException;
    public void changeBalance(double newBalance) throws RemoteException;
}
