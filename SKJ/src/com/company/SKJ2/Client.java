package com.company.SKJ2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {
    public static void main(String[] args) throws IOException {
        var ip = "localhost";
        var port = 10000;
        var address = new InetSocketAddress(ip, port);

        var message = "message";
        var encoding = Charset.forName("ASCII");

        var endcodedMessage = encoding.encode(message);

        try (var connection = SocketChannel.open(address)) {
            connection.write(endcodedMessage);

            var response = ByteBuffer.allocate(1024);
            connection.read(response);
            response.flip();

            var decodeResponse = encoding.decode(response);
            System.out.println(decodeResponse.toString());
        } catch (Exception e) {
        }
    }
}
