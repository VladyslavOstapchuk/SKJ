package com.company.SKJ3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPClient {

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getLocalHost();
//        InetAddress address = InetAddress.getByName("localhost");
        int port = 12345;

        int n = 5;

        byte[] queryBuff = String.valueOf(n).getBytes();
        DatagramPacket query = new DatagramPacket(queryBuff, queryBuff.length, address, port);

        DatagramSocket socket = new DatagramSocket();

        socket.send(query);
        System.out.println("Number sent: "  + n);

        byte[] buff = new byte[UDP.MAX_DATAGRAM_SIZE];
        DatagramPacket packet = new DatagramPacket(buff, buff.length);

        System.out.println("Waiting for server response...");
        socket.receive(packet);

        String str = new String(packet.getData(), 0, packet.getLength()).trim();

        System.out.println("Received from server:"  + str);

        socket.close();
    }

}