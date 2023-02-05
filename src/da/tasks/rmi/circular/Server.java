package da.tasks.rmi.circular;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Service service = new ServiceImpl();
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("Service", service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
