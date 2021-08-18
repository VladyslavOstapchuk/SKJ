package com.company;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Server {
    ServerSocketChannel serverSocket;

    public static void main(String[] args) {
        var ip = "localhost";
        var port = 10000;
        var address = new InetSocketAddress(ip, port);

        ServerSocketChannel server = null;
        try {
            server = ServerSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            server.bind(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

        var encoding = Charset.forName("ASCII");

        while (true) {
            SocketChannel connection = null;
            try {
                connection = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SocketChannel finalConnection = connection;
            Runnable handle = () -> handleConnection(finalConnection, encoding);

            var handler = new Thread(handle);
            handler.start();
        }
    }

    private static void handleConnection(SocketChannel connection, Charset encoding) {
        try (connection) {
            handleConnectionCore(connection, encoding);
        }
        catch (Exception e) {}
    }

    private static void handleConnectionCore(SocketChannel connection, Charset encoding) throws Exception {
        var message = ByteBuffer.allocate(1024);
        connection.read(message);
        message.flip();

        var decodeMessage = encoding.decode(message);
        System.out.println(decodeMessage.toString());
        message.rewind();

        connection.write(message);
    }
}
