package com.company.SKJL.SKJ4;

import com.company.SKJ3.TCPServerEchoThread1;

import java.io.*;
import java.net.*;

public class TCPServerEcho {

    public void listenSocket() {
        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(20002); // ServerSocket class - welcome socket
        }
        catch (IOException e) {
            System.out.println("Could not listen");
            System.exit(-1);
        }
        System.out.println("Server listens on port: " + server.getLocalPort());

        while(true) {
            try {
                // Wait for client
                client = server.accept();  // "accept" method is blocking, waiting for request
                // "client" is a new communication socket
            }
            catch (IOException e) {
                System.out.println("Accept failed");
                System.exit(-1);
            }

            // Serve client in a separate thread, non-blocking
            (new TCPServerEchoThread1(client)).start(); // does not wait for thread completion
        }
    }

    public static void main(String[] args) {
//        System.setProperty("line.separator", "\r\n"); // ***** *** Mac users only *** *****
        TCPServerEcho server = new TCPServerEcho();
        server.listenSocket();
    }
}