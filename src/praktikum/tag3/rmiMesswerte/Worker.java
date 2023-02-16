package praktikum.tag3.rmiMesswerte;

import sockets.udp.UDPSocket;

import java.io.IOException;

public class Worker extends Thread {
    private final MesswertServerImpl server;
    public Worker(MesswertServerImpl server) {
        this.server = server;
        start();
    }
    @Override
    public void run() {
        // startet einen UDPSocket und wartet auf Messwerte
        try(UDPSocket socket = new UDPSocket(1088)) {
            while (true) {
                // setzt Messwerte als int auf den Server
                server.setMesswert(Integer.parseInt(socket.receive(10)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
