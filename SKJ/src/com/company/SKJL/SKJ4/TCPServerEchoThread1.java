package com.company.SKJ3;

import java.io.*;
import java.net.*;

public class TCPServerEchoThread1 extends Thread {
    private Socket socket;

    public TCPServerEchoThread1(Socket socket) {
        super();
        this.socket = socket;
    }

    public void run() {
//        System.setProperty("line.separator", "\r\n"); // Mac users only
        try {
            String thread_ID = Long.toString(currentThread().getId());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String line;
            String echo_line;
            line = in.readLine();
            if (line != null) {
                System.out.println("THREAD " + thread_ID + " received: " + line);
                echo_line = "ECHO: " + line;
                System.out.println("THREAD " + thread_ID + " returning: " + echo_line);
                out.println(echo_line);
            }
        } catch (IOException e1) {
            System.out.println("No I/O");
            // do nothing
        }

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("No I/O");
            // do nothing
        }
    }
}