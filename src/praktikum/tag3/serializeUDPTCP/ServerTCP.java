package praktikum.tag3.serializeUDPTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    public static void main(String[] args)  {
        try(ServerSocket serverSocket = new ServerSocket(1088)) {
            while(true) {
                try (Socket socket = serverSocket.accept()) {
                    ObjectInputStream inStream = new ObjectInputStream(
                                    socket.getInputStream());
                    TestObject testObject = (TestObject) inStream.readObject();
                    System.out.println(testObject.name + ", " + testObject.number);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
