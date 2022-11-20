package sockets.tcp;

import java.net.*;

public class TCPServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1250)) {
            while (true) {
                System.out.println("Wartet auf Verbindung");
                try {
                    TCPSocket tcpSocket = new TCPSocket(serverSocket.accept());
                    new Slave(tcpSocket);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Verbindung geschlossen");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
