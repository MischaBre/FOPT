package rmi.central;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {
    private final DataImpl dataImpl;
    private boolean isExported;

    public ServiceImpl() throws RemoteException {
        super();
        this.dataImpl = new DataImpl();
        isExported = false;
        switchExportStatus();
    }

    @Override
    public synchronized Data open() throws RemoteException {
        if (!isExported) {
            switchExportStatus();
        }
        return dataImpl;
    }

    @Override
    public synchronized Data close() throws RemoteException {
        if (isExported) {
            switchExportStatus();
        }
        return dataImpl;
    }

    @Override
    public synchronized Data get() throws RemoteException {
        return dataImpl;
    }

    @Override
    public synchronized boolean isOpen() throws RemoteException {
        return isExported;
    }

    private void switchExportStatus() throws RemoteException {
        if (isExported) {
            UnicastRemoteObject.unexportObject(dataImpl, false);
        } else {
            UnicastRemoteObject.exportObject(dataImpl, 0);
        }
        isExported = !isExported;
    }

    private DataImpl makeDeepCopyOfData() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(dataImpl);
        oos.flush();
        oos.close();

        ByteArrayInputStream bas = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bas);
        return (DataImpl) ois.readObject();
    }
}
