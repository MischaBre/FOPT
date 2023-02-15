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
        try(UDPSocket socket = new UDPSocket(1088)) {
            while (true) {
                server.setMesswert(Integer.parseInt(socket.receive(10)));
                System.out.println("received: " + server.getMesswert());
                for (MesswertClient cl : server.getClients()) {
                    if (server.getMesswert() >= cl.getMin() && server.getMesswert() <= cl.getMax()) {
                        cl.printWert(server.getMesswert());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
