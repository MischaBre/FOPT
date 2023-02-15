package praktikum.tag3.rmiMesswerte;

import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            MesswertClientImpl messClientEins = new MesswertClientImpl("hans", 0,25);
            MesswertClientImpl messClientZwo = new MesswertClientImpl("peter", 20, 90);
            MesswertClientImpl messClientDrei = new MesswertClientImpl("jupp", 70, 100);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
