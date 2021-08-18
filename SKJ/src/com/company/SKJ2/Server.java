package com.company.SKJ2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;

public class Server {
    public static void main(String[] args) throws IOException {
        var ip = "localhost";
        var port = 10000;
        var address = new InetSocketAddress(ip, port);

        var server = ServerSocketChannel.open();
        server.bind(address);

        var ecnoding = Charset.forName("ASCII");

        try (var connection = server.accept()) {
            var message = ByteBuffer.allocate(1024);
            connection.read(message);
            message.flip();

            var decodedMessage = ecnoding.decode(message);
            System.out.println(decodedMessage.toString());
            message.rewind();

            connection.write(message);
        }
    }
}
