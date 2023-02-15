package praktikum.tag3.parallelUDP;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class StaticSlave extends Thread {
    private final DatagramSocket socket;
    public StaticSlave(DatagramSocket socket) {
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                byte[] bytes = new byte[10];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                ByteArrayInputStream inStream = new ByteArrayInputStream(packet.getData());
                DataInputStream dataInputStream = new DataInputStream(inStream);
                int time = dataInputStream.readInt();

                System.out.println(Thread.currentThread().getName() + ": Ich schlafe: " + time + " Sekunden");
                Thread.sleep(time * 1000L);

                byte[] answer = ("Aufgewacht nach " + time + " Sekunden").getBytes();
                DatagramPacket packetAnswer = new DatagramPacket(answer, answer.length, address, port);
                socket.send(packetAnswer);
                System.out.println(Thread.currentThread().getName() + ": Aufgewacht");

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
