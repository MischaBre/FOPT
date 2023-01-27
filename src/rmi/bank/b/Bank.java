package rmi.bank.b;

import java.io.Serializable;
import java.rmi.Remote;

public interface Bank extends Remote, Serializable {
    public Account getAccount(int i);
}
