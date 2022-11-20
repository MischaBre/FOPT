package sockets.udp;

import java.io.IOException;

public class MCServer {
    public static void main(String[] args) {
        if(args.length != 1)
        {
            System.out.println("Notwendiges Kommandozeilenargument: <Multicast Adresse>");
            return;
        }

        try (UDPMulticastSocket mcSocket = new UDPMulticastSocket(1250)) {
            System.out.println("MC erzeugt");
            mcSocket.join(args[0]);
            System.out.println("MC-Gruppe beigetreten");

            while (true) {
                String request = mcSocket.receive(200);
                System.out.println("Nachricht erhalten: " +
                        mcSocket.getSenderAddress() + ":" +
                        mcSocket.getSenderPort() + ": " +
                        request);
                mcSocket.reply(request);
                if (request.equals("exit")) {
                    break;
                }
            }
            mcSocket.leave(args[0]);
            System.out.println("MC-Gruppe verlassen");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("MC geschlossen");
    }
}
