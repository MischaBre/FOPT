package praktikum.tag3.rmiMesswerte;

import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            MesswertClientImpl messClientEins = new MesswertClientImpl("hans", 0,25);
            MesswertClientImpl messClientZwo = new MesswertClientImpl("peter", 20, 90);
            MesswertClientImpl messClientDrei = new MesswertClientImpl("jupp", 70, 100);
            MesswertClientImpl messClientVier = new MesswertClientImpl("master", 0, 100);
            Thread.sleep(10000);
            System.out.println("hans raus");
            messClientEins.close();
            Thread.sleep(10000);
            System.out.println("peter raus");
            messClientZwo.close();
            Thread.sleep(10000);
            System.out.println("jupp raus");
            messClientDrei.close();
            Thread.sleep(10000);
            messClientVier.close();
        } catch (InterruptedException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
