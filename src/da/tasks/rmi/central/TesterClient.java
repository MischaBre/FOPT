package da.tasks.rmi.central;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TesterClient {
    public static void main(String[] args) {
        try {
            Service serviceImpl = (Service) Naming.lookup("rmi://localhost/ServiceImpl");
            Data data = serviceImpl.get();
            data.append("blub");
            data.append("blub");
            serviceImpl.get().append("blub");
            for (String s : data.getValues()) {
                System.out.println(s);
            }
            System.out.println("_______next1");
            for (String s : serviceImpl.get().getValues()) {
                System.out.println(s);
            }
            System.out.println("_______next2");
            serviceImpl.close();
            for (String s : serviceImpl.get().getValues()) {
                System.out.println(s);
            }
            serviceImpl.get().append("blub");
            for (String s : serviceImpl.get().getValues()) {
                System.out.println(s);
            }
            System.out.println("_______next3");
            data = serviceImpl.open();
            data.append("hihi");
            for (String s : data.getValues()) {
                System.out.println(s);
            }
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
