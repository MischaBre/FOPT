package rmi.bank.c;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private Bank bank;

    public Server(int port) throws RemoteException {
        Registry registry = LocateRegistry.getRegistry(port);
        bank = new BankImpl();
        registry.rebind("Bank", bank);
    }
    public static void main(String[] args) {
        try {
            Server server = new Server(1099);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
