package praktikum.tag3.rmiexport;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Demo extends UnicastRemoteObject implements IDemo {
    public Demo() throws RemoteException {}

    @Override
    public void increment(IData data) throws RemoteException {
        data.setNumber(data.getNumber() + 1);
    }
}
