package sockets.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMIServer {
    public static void main(String[] args) {
        try {
            CounterImpl myCounter = new CounterImpl();
            Naming.bind("Counter", myCounter);
            System.out.println("Zähler Server gestartet");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
