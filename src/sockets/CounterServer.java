package sockets;

import java.io.IOException;

public class CounterServer {
    public static void main(String[] args) {
        int counter = 0;
        try (UDPSocket udpSocket = new UDPSocket(1250)) {
            System.out.println("Server wartet auf Kunden");

            while (true) {
                String request = udpSocket.receive(20);
                switch (request) {
                    case "increment": {
                        System.out.println("Zähler auf " + counter + " durch " +
                                udpSocket.getSenderAddress() + ":" +
                                udpSocket.getSenderPort() +
                                " erhöht");
                        counter++;
                        break;
                    }
                    case "decrement": {
                        counter--;
                        break;
                    }
                    case "reset": {
                        counter = 0;
                        System.out.println("Zähler auf 0 zurückgesetzt durch " +
                                udpSocket.getSenderAddress() + ":" +
                                udpSocket.getSenderPort());
                        break;
                    }
                    default: {
                        System.out.println("unbekannte Anweisung von " +
                                udpSocket.getSenderAddress() + ":" +
                                udpSocket.getSenderPort());
                    }
                }
                String answer = String.valueOf(counter);
                udpSocket.reply(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("UDPSocket wurde geschlossen");
    }
}
