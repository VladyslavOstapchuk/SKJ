package com.company.SKJ3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TCPClient1 {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        Instant time0 = null;
        int firstPort = 30362; // здесь вводишь начальный порт, от которого нужно сканировать
        int lastPort = 30940; // здесь вводишь последний порт, до которого нужно сканировать
        int count = 0;
        for(int i = firstPort; i<lastPort; i++) {
            try {

                SocketAddress sockaddr = new InetSocketAddress("172.21.48.140", i); // здесь вводишь айпишку которую тебе дадут в таске
                //         System.out.println("Socket address: " + sockaddr);


                time0 = Instant.now();
                socket = new Socket();
                socket.connect(sockaddr, 70);
//                System.out.println("Socket connect [ms]: " + ChronoUnit.MILLIS.between(time0, Instant.now()));
                socket.setSoTimeout(100);
//                System.out.println("Socket timeout set to [ms]: " + socket.getSoTimeout());

                String line;
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                if(socket.isConnected()){
                    System.out.println("One found");
                    count++;
                    out.println(196491);
                    System.err.println(in.readLine());
                }

            } catch (UnknownHostException e) {
                System.out.println("?");
                //System.exit(-1);
            } catch (IOException e) {
//                System.out.println("E");
//                System.out.println("Socket error [ms]: " + ChronoUnit.MILLIS.between(time0, Instant.now()));
//            System.exit(-1);
            }
        }
        System.out.println(count);

        try{
            time0 = Instant.now();
            String request_line = "GET / HTTP/1.1";
            String host_reader = "Host: " + "www.pjwstk.edu.pl";

            out.println(request_line);
            out.println(host_reader);
            out.println();

            String line;

            if((line = in.readLine()) != null){
                System.out.println(line);
            }

            System.out.println("Server response [ms]: " + ChronoUnit.MILLIS.between(time0, Instant.now()));
            ;
        } catch (IOException e) {
            System.out.println("Error during communication");
            System.out.println("Error after [ms]: " + ChronoUnit.MILLIS.between(time0,Instant.now()));
            System.exit(-1);
        }

    }
}
