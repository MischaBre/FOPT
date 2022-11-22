package sockets.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Falsche Anzahl an Argumenten");
            return;
        }
        try {
            Counter myCounter = (Counter) Naming.lookup(
                    "rmi://" + args[0] + "/Counter");
            myCounter.reset();
            int count = Integer.parseInt(args[1]);
            long startTime = System.currentTimeMillis();
            int result = 0;
            for (int i = 0; i < count; i++) {
                result = myCounter.increment();
            }
            System.out.println("Hat gedauert: " + (System.currentTimeMillis() - startTime)/1000);
            System.out.println("Letzter Zählerstand: " + result);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
