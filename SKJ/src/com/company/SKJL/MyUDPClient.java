package com.company.SKJL;

import java.io.*;
import java.net.*;

public class MyUDPClient {
    static int serverPort = 1501;
    static DatagramSocket socket;
    static InetAddress address;
    static byte[] buffer;
    static DatagramPacket packet;
    static String message, continueOperation;
    static BufferedReader br;

    public static void main(String arg[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            socket = new DatagramSocket();
            address = InetAddress.getByName("127.0.0.1");
            buffer = new byte[256];
            packet = new DatagramPacket(buffer, buffer.length, address, serverPort);
            socket.send(packet);
            System.out.println("Sending request ");
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            message = new String(packet.getData());
            System.out.println("Received message : " + message.trim());
            System.out.println("Do you want continue (Yes/No) : ");
            continueOperation = br.readLine();
            if (continueOperation.toLowerCase().equals("No")) break;
        }
        /* Закрывается объект сокет */
        socket.close();
    }
}