package da.tasks.rmi.circular;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class ServiceImpl extends UnicastRemoteObject implements Service {

    private final List<Processor> processors;
    private final Data data;
    public final RMISemaphoreImpl lock;

    public ServiceImpl() throws RemoteException {
        processors = new LinkedList<>();
        data = new DataImpl();
        lock = new RMISemaphoreImpl(1);

        Registry registry = LocateRegistry.getRegistry(1099);
        registry.rebind("ServiceSemaphore", lock);
    }

    @Override
    public synchronized void add(Processor p) throws RemoteException {
        lock.p();
        processors.add(p);
        if (processors.size() == 1) {
            Thread t = new Thread(() -> {
                try {
                    p.execute(data);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
            t.start();
            System.out.println("Los gehts");
        }
        lock.v();
    }

    @Override
    public synchronized void remove(Processor p) throws RemoteException {
        lock.p();
        processors.remove(p);
        lock.v();
    }

    @Override
    public synchronized Processor next(Processor p) throws RemoteException {
        if (processors.isEmpty()) {
            return null;
        }
        if (processors.indexOf(p) != processors.size() - 1) {
            return processors.get(processors.indexOf(p) + 1);
        }
        return processors.get(0);
    }
}
