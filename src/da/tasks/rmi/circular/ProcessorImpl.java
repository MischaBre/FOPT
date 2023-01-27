package da.tasks.rmi.circular;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProcessorImpl extends UnicastRemoteObject implements Processor {

    private Service service;

    public ProcessorImpl(Service service) throws RemoteException {
        this.service = service;
    }

    @Override
    public void execute(Data d) throws RemoteException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        d.append(Thread.currentThread().getName() + ": " + System.currentTimeMillis() / 1000);

        Processor nextP = service.next(this);
        Thread t = new Thread(() -> {
            try {
                nextP.execute(d);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}
