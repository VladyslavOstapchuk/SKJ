package com.company.SKJL;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressClass {
    public static void main(String[] args) {
        try{
            //Class allowing to set hostname and IP address, which are necessary for creating connection
            InetAddress address = InetAddress.getLocalHost();
            String addressHost = address.getHostAddress();

            System.out.println("IP address : " + addressHost);
            System.out.println("Hostname : " + address.getHostName());
        } catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}
