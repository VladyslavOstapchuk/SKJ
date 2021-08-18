package com.company.SKJ2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class SuperServer {
    public static void main(String[] args) throws IOException {
        var ip = "localhost";
        var port = 10000;
        var adress = new InetSocketAddress(ip, port);

        var server = ServerSocketChannel.open();
        server.bind(adress);

        var encoding = Charset.forName("ASCII");

        while (true) {
            var connection = server.accept();
            Runnable handle = () -> handleConnection(connection, encoding);

            var handler = new Thread(handle);
            handler.start();
        }
    }

    private static void handleConnection(SocketChannel connection, Charset encoding) {
        try (connection) {
            handleConnectionCore(connection, encoding);
        } catch (Exception e) { }
    }

    private static void handleConnectionCore(SocketChannel connection, Charset encoding) throws Exception{
        var message = ByteBuffer.allocate(1024);
        connection.read(message);
        message.flip();

        var decodeMessage = encoding.decode(message);
        System.out.println(decodeMessage.toString());
        message.rewind();

        connection.write(message);
    }
}
