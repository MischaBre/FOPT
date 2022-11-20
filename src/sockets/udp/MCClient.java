package sockets.udp;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

public class MCClient extends Thread {
    private static final int TIMEOUT = 2000;
    private String[] args;

    public MCClient(String[] args) {
        this.args = args;
        start();
    }

    public void run () {
        try {
            sleep(Math.abs(new Random().nextLong() % 2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (args.length < 2) {
            System.out.println("Falsche Anzahl an Argumenten: <MCAdresse> [<Argumente>, ...]");
            return;
        }
        try (UDPSocket udpSocket = new UDPSocket()) {
            udpSocket.setTimeout(TIMEOUT);
            InetAddress serverAddress = InetAddress.getByName(args[0]);
            for (int i = 1; i < args.length - 1; i++) {
                udpSocket.send(args[i], serverAddress, 1250);
                try {
                    while(true) {
                        String reply = udpSocket.receive(200);
                        System.out.println(Thread.currentThread().getName() +
                                ": Nachricht erhalten: " +
                                udpSocket.getSenderAddress() + ":" +
                                udpSocket.getSenderPort() + ": " +
                                reply);
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }
}
