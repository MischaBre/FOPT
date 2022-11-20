package sockets.udp;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SocketTests {
    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getByName("www.bernard-gruppe.com");
            System.out.println(ia.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
