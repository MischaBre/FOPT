package sockets.tcp;

import java.net.*;

public class Slave extends Thread {
    private final TCPSocket tcpSocket;

    public Slave(TCPSocket tcpSocket) {
        this.tcpSocket = tcpSocket;
        start();
    }

    public void run() {
        System.out.println("Neue Verbindung");
        try(TCPSocket s = tcpSocket) {
            while (true) {
                String request = s.recieveLine();
                if (request == null) {
                    System.out.println("Schließen der Verbindung");
                    break;
                }
                try {
                    int secs = Integer.parseInt(request);
                    Thread.sleep(secs * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                s.sendLine(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Verbindung geschlossen");
    }
}
