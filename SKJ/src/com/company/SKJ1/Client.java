package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class Client {
    public static void main(String[] args)  {
        String ip="localhost";
        int port = 10000;

        String message = "message";
        Charset charset = Charset.defaultCharset();

        ByteBuffer encodeMessage = charset.encode(message);

        try(Socket socket = new Socket(ip,port)){
            OutputStream output = socket.getOutputStream();
            output.write(encodeMessage.array());
            output.flush();

            InputStream input = socket.getInputStream();

            ByteBuffer response = ByteBuffer.allocate(1024);
            int bytesCount = input.read(response.array());
            response.limit(bytesCount);

            CharBuffer decodedResponse = charset.decode(response);
            System.out.println(decodedResponse.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}