package rmi.bank.a;

import rmi.bank.b.OverdrawAccountException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AccountImpl extends UnicastRemoteObject implements Account {
    private double balance;

    public AccountImpl(double balance) throws RemoteException {
        this.balance = balance;
    }

    @Override
    public synchronized double readBalance() throws RemoteException {
        return balance;
    }

    @Override
    public synchronized void changeBalance(double diffBalance) throws RemoteException {
        balance += diffBalance;
    }

}
