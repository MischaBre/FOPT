package praktikum.tag3.rmiexport;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client {
    public static void main(String[] args) {
        try {
            IData data = new Data(); // 7
            UnicastRemoteObject.exportObject(data, 1112);
            data.getNumber(); // get 7
            IDemo demo = (IDemo) Naming.lookup("rmi://localhost:1111/Demo");
            demo.increment(data); // set 8
            demo.increment(data); // set 9
            data.getNumber(); // get 9
            UnicastRemoteObject.unexportObject(data, true);
            demo.increment(data); // set 10, get 9
            demo.increment(data); // set 10, get 9
            data.getNumber(); // get 9

        } catch (NotBoundException | MalformedURLException | RemoteException e) {

        }
    }
}
