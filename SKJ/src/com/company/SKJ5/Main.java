package com.company.SKJ5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main
{
    public static void main(String Args[]) throws IOException
    {
        Socket socket=null;
        PrintWriter out=null;
        BufferedReader in=null;
        String adress=" 172.21.48.182"; //Adres serwera podany w poleceniu
        int port=20007; //Port serwera podany w poleceniu

        socket=new Socket(); //Obiekt gniazda
        socket.connect(new InetSocketAddress(adress,port)); //Gniazdo TCP na konkretny adres i port
        out=new PrintWriter(socket.getOutputStream(),true); //Wysyłanie do serwera
        in=new BufferedReader(new InputStreamReader(socket.getInputStream())); //Odbieranie od serwera

        //Wysyłanie danych do serwera
        out.println(18423);//Mój numer studenta
        out.println("172.23.129.45:30010"); //Adres ip i port na ktory mam wysłac

        //Odbieranie danych od serwera
        String resp=in.readLine();

        //Wyświetlanie danych na konsoli
        System.out.println(resp);

        //Zamykanie strumieni
        socket.close();
        in.close();
        out.close();
    }
}
