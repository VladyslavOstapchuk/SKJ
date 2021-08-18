package com.company.SKJL.SKJ4;

import java.io.*;
import java.net.*;

public class TCPServerEchoThread2 extends Thread {
    private Socket socket;

    public TCPServerEchoThread2(Socket socket) {
        super();
        this.socket = socket;
    }

    public void run() {
        try {
            String thread_ID = Long.toString(currentThread().getId());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String line;
           // String echo_line;
            line = in.readLine();
            out.println("18919");
            out.println("956302");
            String lineN ="aaa";
            String line2="bbb";
            int count = 0;
            if (line != null) {
                if(count==0){
                    lineN = line;
                }
               if(count==1){
                   line2=line;
               }

                count++;
            }
            out.println(lineN + line2);
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
