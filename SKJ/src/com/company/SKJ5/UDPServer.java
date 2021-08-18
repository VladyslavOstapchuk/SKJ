package com.company.SKJ5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class UDPServer {
    private DatagramSocket server;
    static private int MIN_MTU = 576;
    static private int MAX_IP_HEADER_SIZE = 60;
    static private int UDP_HEADER_SIZE = 8;
    static public int MAX_DATAGRAM_SIZE = MIN_MTU - MAX_IP_HEADER_SIZE - UDP_HEADER_SIZE;
    public UDPServer() throws SocketException  {
        initializeServer();
    }

    private void initializeServer() throws SocketException {
        server = new DatagramSocket();
        System.out.println("Server listens on: " + server.getLocalPort());
    }

    private void service() throws IOException {
        byte[] buff = new byte[UDPServer.MAX_DATAGRAM_SIZE];
        DatagramPacket datagram = new DatagramPacket(buff, buff.length);
        System.out.println("Waiting...");

        server.receive(datagram);
        new Thread(() ->  {
            //s-ka
            byte[] bytesS = "18423".getBytes();
            //liczba z polecenia
            byte[] bytesCode = "23987".getBytes();

            int clientPort = datagram.getPort();
            InetAddress clientAddress = datagram.getAddress();
            DatagramPacket resp = new DatagramPacket(bytesS, bytesS.length, clientAddress, clientPort);
            try {
                server.send(resp);
            } catch (IOException e) {
                System.out.println("Error?");
            }
            resp = new DatagramPacket(bytesCode, bytesCode.length, clientAddress, clientPort);
            try {
                server.send(resp);
            } catch (IOException e) {
                System.out.println("Error?");
            }

            try {
                byte[] buffR = new byte[UDPServer.MAX_DATAGRAM_SIZE];
                byte[] buffR2 = new byte[UDPServer.MAX_DATAGRAM_SIZE];
                DatagramPacket n = new DatagramPacket(buffR, buffR.length);
                DatagramPacket x = new DatagramPacket(buffR2, buffR2.length);
                server.receive(n);
                server.receive(x);
                System.out.println(clientAddress+":"+clientPort+" --- "+new String(n.getData(), 0, n.getLength()).trim());
                System.out.println(clientAddress+":"+clientPort+" --- "+new String(x.getData(),0, x.getLength()).trim());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void listen() {
        while(true) {
            try {
                service();
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    public static void main(String[] args) {
        try {
            new UDPServer().listen();
        } catch (SocketException e) {
            System.out.println("Could not set up the server");
        }
    }

}