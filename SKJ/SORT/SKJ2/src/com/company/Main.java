package com.company;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) throws Exception{
        String ip = "172.21.48.182";
        int port = 20007;
        InetSocketAddress address = new InetSocketAddress(ip, port);

        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(address);

        Charset encoding = Charset.forName("ASCII");

        while(true){
            SocketChannel connection = server.accept();
            Runnable handle = () -> handleConnection(connection, encoding);

            Thread handler = new Thread(handle);
            handler.start();
        }
    }

    private static void handleConnection(SocketChannel connection, Charset encoding) {
        try(connection){
            handleConnectionCore(connection, encoding);
        }
        catch(Exception e) {}
    }

    private static void handleConnectionCore(SocketChannel connection, Charset encoding) throws Exception{
        ByteBuffer message = ByteBuffer.allocate(1024);
        connection.read(message);
        message.flip();

        CharBuffer docodeMessage = encoding.decode(message);
        System.out.println(docodeMessage.toString());
        message.rewind();

        connection.write(message);
    }
}