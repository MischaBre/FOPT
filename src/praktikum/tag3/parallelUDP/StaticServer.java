package praktikum.tag3.parallelUDP;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class StaticServer {
    public static void main(String[] args) {
        List<Thread> helper = new ArrayList<>();
        try {
            DatagramSocket socket = new DatagramSocket(1088);
            for (int i = 0; i < 20; i++) {
                helper.add(new StaticSlave(socket));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
