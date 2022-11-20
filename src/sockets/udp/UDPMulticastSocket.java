package sockets.udp;

import java.io.*;
import java.net.*;

public class UDPMulticastSocket extends UDPSocket {
    public UDPMulticastSocket(int port) throws IOException {
        super(new MulticastSocket(port));
    }

    public void join(String mcAddress) throws IOException {
        InetSocketAddress group = new InetSocketAddress(mcAddress, getSenderPort());
        ((MulticastSocket) socket).joinGroup(group, null);
    }

    public void leave(String mcAddress) throws IOException {
        InetSocketAddress group = new InetSocketAddress(mcAddress, getSenderPort());
        ((MulticastSocket) socket).leaveGroup(group, null);
    }
}
