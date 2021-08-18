package com.company.SKJL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class MyUDPServer {
    final int serverPort = 1501;
    //server socket
    DatagramSocket socket = null;
    //buffer to make reading from console faster
    BufferedReader in = null;
    //var storing response
    String message = null;
    //container for response
    byte[] buffer;
    //object necessary for creating connection
    DatagramPacket packet;
    //server port and address
    InetAddress address;
    int port;

    public MyUDPServer() throws IOException {
        //initialization socket
        socket = new DatagramSocket(serverPort);
        call();
    }

    public void call() {
        try {
            while (true) {
                //
                buffer = new byte[256];
                packet = new DatagramPacket(buffer, buffer.length);
                //get message from client
                socket.receive(packet);
                //check if it's empty
                if (packet == null) break;
                System.out.println("Request string for sending to client ");
                //creating buffer to make it faster
                try {
                    //reading from console to the buffer
                    in = new BufferedReader(new InputStreamReader(System.in));
                } catch (Exception e) {
                    System.out.println("Error : " + e);
                }

                //packing message to the container
                message = in.readLine();
                buffer = message.getBytes();
                //getting address and port of client
                address = packet.getAddress();
                port = packet.getPort();
                //sending response to the client
                packet = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(packet);
            }
            //closing buffer
            in.close();
            //closing socket
            socket.close();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public static void main(String args[]) throws Exception {
        //Running server
        new MyUDPServer();
    }
}