package rmi.bank.b;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private Bank bank;

    public Server(int port) throws RemoteException, MalformedURLException {
        Registry registry = LocateRegistry.getRegistry(port);
        bank = new BankImpl();
        for (int i = 0; i < 100; i++) {
            registry.rebind("Konto" + i, bank.getAccount(i));
        }
    }
    public static void main(String[] args) {
        try {
            Server server = new Server(1099);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
