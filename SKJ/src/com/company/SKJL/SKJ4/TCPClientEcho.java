package com.company.SKJL.SKJ4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class TCPClientEcho {
    public static void main(String args[]) throws IOException {
//        System.setProperty("line.separator", "\r\n"); // Mac users only

        //  String host_address =  "172.21.48.171";
        //  int port = 20002;
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        Instant time0 = null;
        int n = 0;
        int i = 34135;
        while (i != 34638) {
            int x=0;
            try {
                time0 = Instant.now();

                InetAddress addr = InetAddress.getByName("172.21.48.163");
              //  System.out.println("Address: " + addr);


                socket = new Socket();


                SocketAddress socketAddress = new InetSocketAddress("172.21.48.163", i);
                socket.connect(socketAddress, 1000);
                i++;


                // System.out.println("will connect to " + socketAddress);
                //socket.connect(socketAddress);
                //timeout handshake'u

                System.out.println("Socket connected [ms]: " + ChronoUnit.MILLIS.between(time0, Instant.now()));

                socket.setSoTimeout(1000); //timeout do komunikacji
                System.out.println("Socket timeout set to [ms]: " + socket.getSoTimeout());

                //   System.exit(0);

                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (UnknownHostException e) {
                System.out.println("Unknown host");
                x++;
             //   System.exit(-1);
            } catch (IOException e) {
                System.out.println("No I/O");
                System.out.println("Error after [ms]: " + ChronoUnit.MILLIS.between(time0, Instant.now()));
                x++;

               // System.exit(-1);
            }

            try {


                time0 = Instant.now();
                if(x==0) {
                    n++;
                out.println("74823");
                out.println();

                String line;


                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                    System.out.println(n);
                }

                System.out.println("Server response [ms]: " + ChronoUnit.MILLIS.between(time0, Instant.now()));
            } catch (IOException e) {
                System.out.println("Error during communication");
                System.out.println("Error after [ms]: " + ChronoUnit.MILLIS.between(time0, Instant.now()));
                System.exit(-1);
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Cannot close the socket");
                System.exit(-1);
            }
        }
    }
}