package da.tasks.rmi.circular;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMISemaphore extends Remote, Serializable {
    void p() throws RemoteException;
    void v() throws RemoteException;
}
