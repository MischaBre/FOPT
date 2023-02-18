package praktikum.tag3.rmiexport;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            IDemo demo = new Demo();
            Registry registry = LocateRegistry.createRegistry(1111);
            registry.rebind("Demo", demo);
        } catch (RemoteException e) {

        }
    }
}
