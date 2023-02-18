package sockets.uebung;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class SocketTests {
    public static void main(String[] args) {
        for (String s : args) {
            try {
                InetAddress[] result = InetAddress.getAllByName(s);
                for (InetAddress i : result) {
                    System.out.print(i.getHostAddress());
                    System.out.print(", ");
                }
            } catch (UnknownHostException e) {
                System.out.println(s + " nicht auflösbar");
            }
            System.out.println();
        }
    }
}
