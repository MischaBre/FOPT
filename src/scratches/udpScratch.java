package scratches;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPSocket implements AutoCloseable {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    public UDPSocket(int port) throws IOException {
        socket = new DatagramSocket(port);
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    public String receive(int maxLength) throws IOException {
        byte[] result = new byte[maxLength];
        DatagramPacket packet = new DatagramPacket(result, maxLength);
        socket.receive(packet);
        address = packet.getAddress();
        port = packet.getPort();
        return new String(result, 0, packet.getLength());
    }

    public void send(String msg, InetAddress recieverAddress, int recieverPort) throws IOException {
        byte[] outBuffer = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(outBuffer, outBuffer.length, recieverAddress, recieverPort);
        socket.send(packet);
    }

    public void reply(String msg) throws IOException {
        if (address == null) {
            System.out.println("wohin damit?");
        }
        send(msg, address, port);
    }
}

class UDPServer {
    public static void main(String[] args) {
        try (UDPSocket socket = new UDPSocket(1250)) {
            while(true) {
                String command = socket.receive(20);
                System.out.println("Bekommen: " + command);
                socket.reply("Zurück: " + command);
            }
        } catch (Exception e) {
            System.out.println("Verbindung geschlossen");
        }
    }
}

class UDPServerBare {
    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket()) {
            while(true) {
                byte[] b = new byte[20];
                DatagramPacket p = new DatagramPacket(b, b.length);
                socket.receive(p);
                String s = new String(b, 0, p.getLength());
                socket.send(new DatagramPacket(s.getBytes(), s.length(),socket.getInetAddress(), socket.getPort()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}