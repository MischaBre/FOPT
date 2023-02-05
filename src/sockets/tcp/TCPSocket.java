package sockets.tcp;

import java.io.*;
import java.net.*;

public class TCPSocket implements AutoCloseable {

    private Socket socket;
    private BufferedReader istream;
    private BufferedWriter ostream;

    public TCPSocket(String serverAddress, int serverPort) throws UnknownHostException, IOException {
        socket = new Socket(serverAddress, serverPort);
        initializeStream();
    }

    public TCPSocket(Socket socket) throws UnknownHostException, IOException {
        this.socket = socket;
        initializeStream();
    }

    public void close() throws IOException {
        socket.close();
    }

    public void sendLine(String s) throws IOException {
        ostream.write(s);
        ostream.newLine();
        ostream.flush();
    }

    public String recieveLine() throws IOException {
        return istream.readLine();
    }

    public void setSoTimeout(int timeout) throws SocketException {
        socket.setSoTimeout(timeout);
    }

    private void initializeStream() throws IOException {
        ostream = new BufferedWriter(
                new OutputStreamWriter(
                        socket.getOutputStream()));
        istream = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
    }
}
