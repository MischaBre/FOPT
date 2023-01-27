package rmi.bank.b;

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
        if (diffBalance < 0 && Math.abs(diffBalance) > balance) {
            throw new OverdrawAccountException("zu wenig Geld");
        }
        balance += diffBalance;
    }

}
