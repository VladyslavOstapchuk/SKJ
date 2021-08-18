package com.company.SKJ5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;


public class UDPClient {
    static private int MIN_MTU = 576;
    static private int MAX_IP_HEADER_SIZE = 60;
    static private int UDP_HEADER_SIZE = 8;
    static public int MAX_DATAGRAM_SIZE = MIN_MTU - MAX_IP_HEADER_SIZE - UDP_HEADER_SIZE;

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        //adres i port z polecenia
        String hostname =  "172.21.48.182";
        int port = 20003;

        PrintWriter out = null;
        BufferedReader in = null;
        String address = null;
        socket = new Socket();
        socket.connect(new InetSocketAddress(hostname, port));
        out = new PrintWriter(socket.getOutputStream(), true);
        //s-ka
        out.println("18423");
        //twoj adres uzyskany przez ipconfig i numer portu który ci wypisuje serwer przy go uruchomieniu
        out.println("172.23.129.190:50692");
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(in.readLine());
        String s;
        while ((s = in.readLine())!=null){
            System.out.println(s);
        }
    }
}