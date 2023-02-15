package praktikum.tag3.parallelUDP;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DynamicSlave extends Thread {
    private final DatagramPacket packet;
    private final DatagramSocket socket;
    public DynamicSlave(DatagramPacket packet, DatagramSocket socket) {
        this.packet = packet;
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        try {
            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            ByteArrayInputStream inStream = new ByteArrayInputStream(packet.getData());
            DataInputStream dataInputStream = new DataInputStream(inStream);
            int time = dataInputStream.readInt();

            System.out.println(Thread.currentThread().getName() + ": Ich schlafe: " + time + " Sekunden");
            Thread.sleep(time* 1000L);

            byte[] answer = "aufgewacht".getBytes();
            DatagramPacket packetAnswer = new DatagramPacket(answer, answer.length, address, port);
            socket.send(packetAnswer);
            System.out.println(Thread.currentThread().getName() + ": Aufgewacht");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
