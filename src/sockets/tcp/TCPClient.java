package sockets.tcp;

public class TCPClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("falsche anzahl an argumenten");
            return;
        }
        System.out.println("Aufbau der Verbindung");
        try (TCPSocket tcpSocket = new TCPSocket(args[0], 1250)) {
            tcpSocket.sendLine("reset");
            String reply = tcpSocket.recieveLine();

            int count = Integer.parseInt(args[1]);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                tcpSocket.sendLine("increment");
                reply = tcpSocket.recieveLine();
            }
            long duration = System.currentTimeMillis() - startTime;
            System.out.println("Zeit: " + duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("TCP Verbindung geschlossen");
    }
}
