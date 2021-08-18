package com.company;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {
    public static void main(String[] args) {
        var ip = "172.21.48.182";
        var port = 20007;
        var adress = new InetSocketAddress(ip,port);

        var message = "18423\n";
        var encoding = Charset.forName("ASCII");

        var encodedMessage = encoding.encode(message);

        try(var connection = SocketChannel.open(adress)){
            connection.write(encodedMessage);

            var response = ByteBuffer.allocate(1024);
            connection.read(response);
            response.flip();

            var decodedResponse = encoding.decode(response);
            System.out.println(decodedResponse.toString());
        }catch (Exception e){}
    }
}
