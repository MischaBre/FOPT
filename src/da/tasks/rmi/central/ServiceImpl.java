package da.tasks.rmi.central;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {
    private DataImpl dataImpl;
    private boolean isOpen;

    public ServiceImpl() throws RemoteException {
        this.dataImpl = new DataImpl();
        isOpen = true;
    }

    @Override
    public synchronized Data open() throws RemoteException {
        isOpen = true;
        return get();
    }

    @Override
    public synchronized Data close() throws RemoteException {
        isOpen = false;
        return get();
    }

    @Override
    public synchronized Data get() throws RemoteException {
        if (isOpen) {
            // System.out.println(dataImpl);
            // return (Data) UnicastRemoteObject.exportObject(dataImpl,0); ?????
            return dataImpl;
        } else {
            Data obj = null;
            try {
                obj = makeDeepCopyOfData();
                // System.out.println(obj);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    @Override
    public synchronized boolean isOpen() throws RemoteException {
        return isOpen;
    }

    private Data makeDeepCopyOfData() throws IOException, ClassNotFoundException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(dataImpl);
        oos.flush();
        oos.close();

        ByteArrayInputStream bas = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bas);
        return (Data) ois.readObject();
    }
}