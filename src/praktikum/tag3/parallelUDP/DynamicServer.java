package praktikum.tag3.parallelUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DynamicServer {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(1088)) {
            while (true) {
                byte[] bytes = new byte[100];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                socket.receive(packet);
                new DynamicSlave(packet, socket);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
