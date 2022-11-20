package sockets.udp;

import java.net.InetAddress;

public class CounterClient {
    private static final int TIMEOUT = 10000;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Falsche Anzahl an Argumenten. Benötigt: <server-name> <number>");
            return;
        }
        try (UDPSocket udpSocket = new UDPSocket()) {
            udpSocket.setTimeout(TIMEOUT);
            InetAddress serverAddress = InetAddress.getByName(args[0]);
            udpSocket.send("reset", serverAddress, 1250);
            String reply = null;
            try {
                reply = udpSocket.receive(20);
                System.out.println("Zähler: " + reply);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int count = Integer.parseInt(args[1]);
            long startTime = System.currentTimeMillis();
            // perform increment "count" number of times
            for(int i = 0; i < count; i++) {
                udpSocket.send("increment", serverAddress, 1250);
                try {
                    reply = udpSocket.receive(20);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            // display statistics
            long stopTime = System.currentTimeMillis();
            long duration = stopTime - startTime;
            System.out.println("Gesamtzeit = " + duration + " msecs");
            if(count > 0) {
                System.out.println("Durchschnittszeit = "
                        + ((duration) / (float) count)
                        + " msecs");
            }
            System.out.println("Letzter Zählerstand: " + reply);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("DatagramSocket wurde geschlossen");
    }
}
