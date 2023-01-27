package da.tasks.rmi.circular;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DataImpl extends UnicastRemoteObject implements Data {
    private final List<String> values;
    public DataImpl() throws RemoteException {
        values = new ArrayList<>();
    }

    @Override
    public synchronized void append(String s) throws RemoteException {
        values.add(s);
    }

    @Override
    public synchronized List<String> getValues() throws RemoteException {
        return values;
    }
}
