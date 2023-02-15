package praktikum.tag3.rmimultiplikation;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Rechnen rechnen = new RechnenImpl();
            Registry registry = LocateRegistry.createRegistry(1111);
            registry.rebind("Rechnen", rechnen);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
