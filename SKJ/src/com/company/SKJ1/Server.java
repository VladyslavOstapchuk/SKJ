package com.company.SKJ1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class Server {
    public static void main(String[] args){
        String ip = "localhost";
        int port = 10000;
        InetSocketAddress address = new InetSocketAddress(ip,port);

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            serverSocket.bind(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Charset charset = Charset.forName("ASCII");

        try(Socket socket = serverSocket.accept()){
            InputStream inputStream = socket.getInputStream();

            ByteBuffer message = ByteBuffer.allocate(1024);
            int bytesCount = inputStream.read(message.array());
            message.limit(bytesCount);

            CharBuffer decodedMessage = charset.decode(message);
            System.out.println(decodedMessage.toString());

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(message.array(),0,bytesCount);
            outputStream.flush();
        } catch (Exception e){

        }
    }
}
