package sockets.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            Registry registry;
            if (LocateRegistry.getRegistry() == null) {
                registry = LocateRegistry.createRegistry(1099);
            } else {
                registry = LocateRegistry.getRegistry(1099);
            }

            CounterImpl myCounter = new CounterImpl();
            registry.rebind("Counter", myCounter);
            System.out.println("Zähler Server gestartet");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
