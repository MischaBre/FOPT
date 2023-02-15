package praktikum.tag3.serializeUDPTCP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

public class ClientUDP {
    public static void main(String[] args) {
        // Objekt erzeugen
        TestObject test = new TestObject("Digga", 42);
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream(1000);
             ObjectOutputStream objectStream = new ObjectOutputStream(outStream)) {
            // ByteOutputStream und ObjectOutputStream erzeugen, Objekt schreiben, flush() und in byteArray schreiben
            objectStream.writeObject(test);
            objectStream.flush();
            byte[] objectAfterSerialize = outStream.toByteArray();
            // DatagramSocket erzeugen (ohne Port)
            try (DatagramSocket datagramSocket = new DatagramSocket()) {
                // DatagramPacket mit byteArray, Länge, INetAdress.getByName und Port erzeugen
                // über DatagramSocket senden
                DatagramPacket packet = new DatagramPacket(
                        objectAfterSerialize,
                        objectAfterSerialize.length,
                        InetAddress.getByName("localhost"),
                        1088);
                datagramSocket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
