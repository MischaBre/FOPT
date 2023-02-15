package praktikum.tag3.serializeUDPTCP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP {

    public static void main(String[] args) {
        // UDP: Zuerst Datagramsocket mit Port
        try (DatagramSocket datagramSocket = new DatagramSocket(1088)) {
            while (true) {
                // DatagramPacket mit Länge und warten auf receive
                byte[] b = new byte[1000];
                DatagramPacket packet = new DatagramPacket(b, b.length);
                datagramSocket.receive(packet);
                try (ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.getData());
                     ObjectInputStream inStream = new ObjectInputStream(byteStream)) {
                    // ByteInputStream und ObjectInputStream erzeugen, Objekt lesen und casten
                    TestObject test = (TestObject) inStream.readObject();
                    System.out.println(test.name + " - " + test.number);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
