package praktikum.tag3.parallelUDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;

public class Client {
    public static void main(String[] args) {
        for (int t = 0; t < 40; t++) {
            new Thread(() -> {
                try (DatagramSocket socket = new DatagramSocket()) {
                    Thread.sleep((int) (Math.random() * 5000));
                    for (int i = 0; i < 5; i++) {
                        ByteArrayOutputStream outStream = new ByteArrayOutputStream(10);
                        DataOutputStream dataStream = new DataOutputStream(outStream);
                        dataStream.writeInt(i);
                        DatagramPacket packet = new DatagramPacket(
                                outStream.toByteArray(),
                                outStream.size(),
                                InetAddress.getByName("localhost"),
                                1088);
                        LocalTime then = LocalTime.now();
                        socket.send(packet);
                        outStream.reset();

                        byte[] bytes = new byte[100];
                        DatagramPacket result = new DatagramPacket(bytes, bytes.length);
                        socket.receive(result);
                        LocalTime now = LocalTime.now();
                        System.out.println(
                                new String(result.getData(),0, result.getLength()) +
                                " - erhalten nach: " +
                                        (now.toSecondOfDay() - then.toSecondOfDay()) +
                                " Sekunden.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
