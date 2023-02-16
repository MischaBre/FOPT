package praktikum.tag3.rmiMesswerte;

import sockets.udp.UDPSocket;

import java.io.IOException;
import java.net.InetAddress;

public class Messwerterfassung {
    public static void main(String[] args) {
        try (UDPSocket socket = new UDPSocket()) {
            while(true) {
                socket.send(String.valueOf((int) (Math.random()*100)), InetAddress.getByName("localhost"), 1088);
                System.out.println("MWE sent");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
