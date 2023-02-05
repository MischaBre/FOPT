package scratches;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class TCPSocket implements AutoCloseable {
    protected Socket socket;
    private BufferedReader inStream;
    private BufferedWriter outStream;

    public TCPSocket(String serverAddress, int port) throws IOException {
        socket = new Socket(serverAddress, port);
        initializeStreams();
    }

    public TCPSocket(Socket socket) throws IOException {
        this.socket = socket;
        initializeStreams();
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }

    public void setSocketTimeout(int timeoutInMs) throws IOException {
        socket.setSoTimeout(timeoutInMs);
    }

    public void sendLine(String msg) throws IOException {
        outStream.write(msg);
        outStream.newLine();
        outStream.flush();
    }

    public String receiveLine() throws IOException {
        return inStream.readLine();
    }

    private void initializeStreams() throws IOException {
        inStream = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        outStream = new BufferedWriter(
                new OutputStreamWriter(
                        socket.getOutputStream()));
    }
}

class StaticSequentialTCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            while(true) {
                try (TCPSocket socket = new TCPSocket(serverSocket.accept())) {
                    while(true) {
                        String command = socket.receiveLine();
                        if (command != null) {
                            System.out.println("Received " + command);
                            socket.sendLine("Received " + command);
                        } else {
                            System.out.println("Verbindung schließen...");
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Verbindung geschlossen");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DynamicParallelTCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            while(true) {
                TCPSocket socket = new TCPSocket(serverSocket.accept());
                new Thread(() -> {
                    try(TCPSocket s = socket) {
                        while(true) {
                            String command = s.receiveLine();
                            if (command != null) {
                                System.out.println("Received " + command);
                                socket.sendLine("Received " + command);
                            } else {
                                System.out.println("Verbindung schließen...");
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class RestrictedDynamicParallelTCPServer {
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        try(ServerSocket serverSocket = new ServerSocket()) {
            for (Thread t : threads) {
                t = new Thread(() -> {
                    while(true) {
                        try(TCPSocket socket = new TCPSocket(serverSocket.accept())) {
                            while(true) {
                                String command = socket.receiveLine();
                                if (command != null) {
                                    System.out.println("Received " + command);
                                    socket.sendLine("Received " + command);
                                } else {
                                    System.out.println("Verbindung schließen...");
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TCPServerBare {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            while(true) {
                try(Socket socket = serverSocket.accept()) {
                    BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    while (true) {
                        String command = inStream.readLine();
                        if (command != null) {
                            outStream.write(command);
                            outStream.newLine();
                            outStream.flush();
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}