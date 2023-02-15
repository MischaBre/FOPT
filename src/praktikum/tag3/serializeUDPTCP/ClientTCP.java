package praktikum.tag3.serializeUDPTCP;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {
    public static void main(String[] args) {
        TestObject testObject = new TestObject("blub", 42);
        try(Socket socket = new Socket(InetAddress.getByName("localhost"), 1088)) {
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.writeObject(testObject);
            outStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
