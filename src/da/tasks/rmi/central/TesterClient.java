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
            System.out.println(data);
            data.append("blub");
            data.append("blub");
            for (String s : data.getValues()) {
                System.out.println(s);
            }
            System.out.println("_______next1");
            for (String s : data.getValues()) {
                System.out.println(s);
            }
            System.out.println("_______next2");
            data = service.close();
            for (String s : data.getValues()) {
                System.out.println(s);
            }
            System.out.println("_______next2b");
            data.append("blub");
            for (String s : data.getValues()) {
                System.out.println(s);
            }
            System.out.println(data);
            System.out.println("_______next3");
            data = service.open();
            data.append("hihi");
            for (String s : data.getValues()) {
                System.out.println(s);
            }
            System.out.println(data);
            System.out.println("_______next2");
            data = service.close();
            System.out.println(data);
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
