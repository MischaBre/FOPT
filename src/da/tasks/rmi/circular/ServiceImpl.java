package da.tasks.rmi.circular;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class ServiceImpl extends UnicastRemoteObject implements Service {

    private final List<Processor> processors;
    private final Data data;

    public ServiceImpl() throws RemoteException {
        processors = new LinkedList<>();
        data = new DataImpl();
    }

    @Override
    public synchronized void add(Processor p) throws RemoteException {
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
        }
    }

    @Override
    public synchronized void remove(Processor p) throws RemoteException {
        processors.remove(p);
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
