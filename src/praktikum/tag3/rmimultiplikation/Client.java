package praktikum.tag3.rmimultiplikation;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            Rechnen rechnen = (Rechnen) Naming.lookup("rmi://localhost:1111/Rechnen");
            System.out.println(rechnen.multiplikation(1,7));
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
