package da.tasks.rmi.central;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TesterClient {
    public static void main(String[] args) {
        try {
            Service service = (Service) Naming.lookup("rmi://localhost/Service");
            Data data = service.get();
            data.append("blub");
            data.append("blub");
            for (String s : data.getValues()) {
                System.out.println(s);
            }
            System.out.println("_______next1");
            for (String s : service.get().getValues()) {
                System.out.println(s);
            }
            System.out.println("_______next2");
            service.close();
            for (String s : service.get().getValues()) {
                System.out.println(s);
            }
            System.out.println("_______next2b");
            service.get().append("blub");
            for (String s : service.get().getValues()) {
                System.out.println(s);
            }
            System.out.println("_______next3");
            data = service.open();
            data.append("hihi");
            for (String s : data.getValues()) {
                System.out.println(s);
            }
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
