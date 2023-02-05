package da.tasks.rmi.circular;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMISemaphoreImpl extends UnicastRemoteObject implements RMISemaphore {
    private int value;

    public RMISemaphoreImpl(int init) throws RemoteException {
        value = init;
    }

    @Override
    public synchronized void p() throws RemoteException {
        while (value == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value--;
    }

    @Override
    public synchronized void v() throws RemoteException {
        value++;
        notifyAll();
    }
}
