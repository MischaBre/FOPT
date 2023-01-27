package rmi.bank.a;

import java.rmi.RemoteException;

public class BankImpl implements Bank {
    private Account[] accounts;

    public BankImpl() throws RemoteException {
        accounts = new Account[100];
        for (int i = 0; i < 100; i++) {
            accounts[i] = new AccountImpl(0.0);
        }
    }

    @Override
    public synchronized Account getAccount(int i) {
        if (i < 0 || i > 100) {
            throw new IllegalArgumentException("falscher Wert");
        }
        return accounts[i];
    }


}
