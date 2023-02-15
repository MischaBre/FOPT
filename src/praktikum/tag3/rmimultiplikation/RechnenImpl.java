package praktikum.tag3.rmimultiplikation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RechnenImpl extends UnicastRemoteObject implements Rechnen {
    public RechnenImpl() throws RemoteException {
    }

    @Override
    public int multiplikation(int x, int y) throws RemoteException {
        return x*y;
    }
}
