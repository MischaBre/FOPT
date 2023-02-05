package da.tasks.rmi.circular;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProcessorImpl extends UnicastRemoteObject implements Processor {

    private final Service service;
    private RMISemaphore lock;

    public ProcessorImpl(Service service) throws RemoteException {
        this.service = service;
        try {
            lock = (RMISemaphore) Naming.lookup("rmi://localhost/ServiceSemaphore");
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Data d) throws RemoteException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        d.append(Thread.currentThread().getName() + ": " + System.currentTimeMillis() / 1000);
        System.out.print("Values: ");
        for (String s : d.getValues()) {
            System.out.print(s + " ");
        }
        System.out.print("\n");

        lock.p();
        Processor nextP = service.next(this);
        if (nextP == null) {
            System.out.println("Keinen Processor gefunden.");
            return;
        }
        System.out.println("Nächsten Processor gefunden.");
        Thread t = new Thread(() -> {
            try {
                nextP.execute(d);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        t.start();
        System.out.println("Nächster Processor startet");
        lock.v();
    }
}
