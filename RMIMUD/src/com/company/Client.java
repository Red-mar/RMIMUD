package com.company;

import com.company.mud.ViewOnChat;
import com.company.network.RMIClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        boolean isRunning = true;
        Scanner input = new Scanner(System.in);

        System.out.println("Client using registry");

        String ip = "localhost";
        int port = 4321;

        RMIClient client = new RMIClient(ip, port);
        try {
            ViewOnChat viewOnChat = new ViewOnChat();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        while (isRunning){

            switch (input.next()){
                case "say":
                    try {
                        client.sendMessage(input.next());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case "get":
                    try {
                        System.out.println("--- Chat Log ---");
                        for (String s : client.getMessages()) {
                            System.out.println(s);
                        }
                        System.out.println("--- E N D ---");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    isRunning = false;
                    break;
                default:
                    break;
            }
        }
    }
}
