package da.tasks.rmi.central;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DataImpl extends UnicastRemoteObject implements Data {
    private ArrayList<String> data;
    public DataImpl() throws RemoteException {
        data = new ArrayList<>();
    }

    public synchronized void append(String s) throws RemoteException {
        if (s != null && s.length() > 0) {
            data.add(s);
        }
    }

    public synchronized ArrayList<String> getValues() throws RemoteException {
        return data;
    }

    public String miep() {
        String res = "[";
        for (String s : data) {
            res += s + ",";
        }
        return res + "]";
    }
}
