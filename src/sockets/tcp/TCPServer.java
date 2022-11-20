package sockets.tcp;

import java.net.*;

public class TCPServer {

    public static void main(String[] args) {
        int counter = 0;

        try (ServerSocket serverSocket = new ServerSocket(1250)) {
            while (true) {
                System.out.println("Wartet auf Verbindung");
                try (TCPSocket tcpSocket = new TCPSocket(serverSocket.accept())) {
                    while (true) {
                        String request = tcpSocket.recieveLine();
                        if (request == null) {
                            System.out.println("Schließen der Verbindung");
                            break;
                        }
                        switch (request) {
                            case "increment": {
                                counter++;
                                break;
                            }
                            case "reset": {
                                counter = 0;
                                break;
                            }
                            default:
                        }
                        String result = String.valueOf(counter);
                        tcpSocket.sendLine(result);
                    }
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
