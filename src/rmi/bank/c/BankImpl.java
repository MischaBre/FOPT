package rmi.bank.c;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankImpl extends UnicastRemoteObject implements Bank {
    private Account[] accounts;

    public BankImpl() throws RemoteException {
        accounts = new Account[100];
        for (int i = 0; i < 100; i++) {
            accounts[i] = new AccountImpl(0.0);
        }
    }

    @Override
    public synchronized Account getAccount(int num) throws RemoteException {
        if (num < 0 || num > 99) {
            throw new IllegalArgumentException("falscher Wert");
        }
        return accounts[num];
    }


}
