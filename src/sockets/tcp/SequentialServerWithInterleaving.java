package sockets.tcp;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SequentialServerWithInterleaving {
    public static void main(String[] args) throws Exception {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
             ServerSocket serverSocket = serverSocketChannel.socket();
             Selector selector = Selector.open()
        ) {
            serverSocket.bind(new InetSocketAddress(1250));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                System.out.println("Elemente: " + selector.keys().size());
                selector.select();
                Set<SelectionKey> readyKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = readyKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        // Accept-Ereignis
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel sc = ssc.accept();
                        System.out.println("Verbindung angenommen von: " + sc);
                        sc.configureBlocking(false);
                        SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
                        ByteBuffer buffer = ByteBuffer.allocate(128);
                        newKey.attach(buffer);
                    } else if (key.isReadable()) {
                        // Read-Ereignis
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        buffer.clear();
                        int bytes = sc.read(buffer);
                        if (bytes >= 0) {
                            System.out.println(bytes + " Bytes gelesen: ");
                            byte[] array = new byte[buffer.capacity()];
                            buffer.flip();
                            buffer.get(array, 0, bytes);
                            String message = new String(array, 0, bytes - 2);
                            System.out.println(message);
                            try {
                                int secs = Integer.parseInt(message);
                                Thread.sleep(secs * 1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            buffer.flip();
                            sc.write(buffer);
                        } else {
                            sc.close();
                            System.out.println("Verbindung geschlsosen");
                        }
                    } else {
                        // writing
                    }
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
