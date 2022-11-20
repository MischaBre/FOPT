package sockets.udp;

public class MCClientManager {
    public static void main(String[] args) {
        MCClient[] mcClients = new MCClient[20];
        for (MCClient mcc : mcClients) {
            mcc = new MCClient(args);
        }
    }
}
