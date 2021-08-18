package com.company.SKJ5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer2
{
    private DatagramSocket socket;

    public static class ServerThread extends Thread
    {
        private final DatagramPacket packet;
        private final DatagramSocket socket;

        public ServerThread(DatagramPacket packet, DatagramSocket socket)
        {
            super();
            this.packet = packet;
            this.socket = socket;
        }


        public void run()
        {
            try
            {
                String data = new String(packet.getData(),0,packet.getLength());
                String s="18423"; //MOJA ESKA
                String s2="520946"; //LICZBA Z POLECENIA

                byte[] respBuff = s.getBytes();
                int clientPort = packet.getPort();
                InetAddress clientAddress = packet.getAddress();
                DatagramPacket resp = new DatagramPacket(respBuff, respBuff.length, clientAddress, clientPort);
                socket.send(resp);//Wysyłam eske

                respBuff = s2.getBytes();
                resp = new DatagramPacket(respBuff, respBuff.length, clientAddress, clientPort);
                socket.send(resp);//Wysyłam liczbę

                DatagramPacket datagram = new DatagramPacket(respBuff, respBuff.length);
                socket.receive(datagram);//Odbieram n

                String tmp=(new String(datagram.getData()));

                DatagramPacket datagram2 = new DatagramPacket(respBuff, respBuff.length);
                socket.receive(datagram2);//Odbieram x

                String x=new String(datagram2.getData());

                System.out.println(tmp+x);

            } catch (IOException e1)
            {
                // do nothing
            }

        }
    }

    private void service() throws IOException
    {
        byte[] buff = new byte[65535];
        final DatagramPacket datagram = new DatagramPacket(buff, buff.length);

        socket.receive(datagram);

        (new ServerThread(datagram, socket)).start();
    }

    public void listenSocket(int port)
    {

        try
        {
            socket = new DatagramSocket(port);
        }
        catch (IOException e)
        {
            System.out.println("Could not listen");
            System.exit(-1);
        }
        System.out.println("Server listens on port: " + socket.getLocalPort());

        while(true)
        {
            try
            {
                service();
            }
            catch (IOException e)
            {
                System.out.println("Accept failed");
            }
        }

    }

    public static void main(String[] args)
    {
        UDPServer2 server = new UDPServer2();
        server.listenSocket(30011);
    }
}
