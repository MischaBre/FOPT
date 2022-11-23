package da.tasks.rmi.central;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Data extends Remote, Serializable {
    public void append(String s) throws RemoteException;
    public ArrayList<String> getValues() throws RemoteException;
}
