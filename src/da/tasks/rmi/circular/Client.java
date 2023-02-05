package da.tasks.rmi.circular;

import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            Service service = (Service) Naming.lookup("rmi://localhost/Service");
            Processor proc = new ProcessorImpl(service);
            service.add(proc);
            Thread.sleep(100000);
            service.remove(proc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
