package com.company.TEST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;


class UDPClient {
    static private int MIN_MTU = 576;
    static private int MAX_IP_HEADER_SIZE = 60;
    static private int UDP_HEADER_SIZE = 8;
    static public int MAX_DATAGRAM_SIZE = MIN_MTU - MAX_IP_HEADER_SIZE - UDP_HEADER_SIZE;

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        //=============================================================
        String hostname =  "172.21.48.136";
        int port = 20003;
        PrintWriter out = null;
        BufferedReader in = null;
        String address = null;
        socket = new Socket();
        socket.connect(new InetSocketAddress(hostname, port));
        out = new PrintWriter(socket.getOutputStream(), true);
        //==============================================================
        out.println(20128);
        //==============================================================
        out.println("172.23.129.190:52028");
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(in.readLine());
        String s;
        while ((s = in.readLine())!=null){
            System.out.println(s);
        }
    }
}