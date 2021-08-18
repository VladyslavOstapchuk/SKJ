package com.company.SKJL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

class MyTCPServer extends Thread {
    ServerSocket serverSocket;
    final int serverPort = 1001;

    public MyTCPServer() {
        try {
            //Creating ServerSocket class object to provide connection between client and server
            serverSocket = new ServerSocket(serverPort);
            System.out.println(serverSocket.toString());
        } catch (IOException e) {
            fail(e, "Could not start server");
        }
        //inheriting from Thread allows to service multiple clients at the same time
        this.start();
    }

    public void run() {
        try {
            while (true) {
                Socket client = serverSocket.accept();
                Connection connection = new Connection(client);
            }
        } catch (IOException e) {
            fail(e, "Not listening");
        }
    }

    public void fail(Exception e, String message) {
        System.out.println(message + "." + e);
    }
}

class Connection extends Thread {
    protected Socket netClient;
    protected BufferedReader fromClient;
    protected PrintStream toClient;

    public Connection(Socket client) {
        netClient = client;
        try {
            fromClient = new BufferedReader(new InputStreamReader(netClient.getInputStream()));
            toClient = new PrintStream(netClient.getOutputStream());
        } catch (IOException e) {
            try {
                netClient.close();
            } catch (IOException e1) {
                System.out.println("Unable to set up streams" + e1);
                return;
            }
        }
        this.start();
    }

    public void run() {
        String login, password;
        try {
            while (true) {
                toClient.println("Login : ");
                login = fromClient.readLine();

                if (login == null || login.equals("Bye")) {
                    System.out.println("Exit");
                    return;
                } else {
                    System.out.println(login + " logged in");
                    toClient.println("Password: ");
                    password = fromClient.readLine();
                }
            }
        } catch (IOException e) {
        } finally {
            try {
                netClient.close();
            } catch (IOException e) {
            }
        }
    }
}

class Main{
    public static void main(String[] args) {
        new MyTCPServer();
    }
}