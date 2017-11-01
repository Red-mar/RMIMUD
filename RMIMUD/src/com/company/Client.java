package com.company;

import com.company.mud.GameClient;
import com.company.mud.ViewOnChat;
import com.company.network.RMIClient;
import com.company.network.Session;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        boolean isRunning = true;
        Scanner input = new Scanner(System.in);
        GameClient client = null;
        Session session = null;

        System.out.println("Client using registry");

        String ip = "localhost";
        int port = 4321;

        try {
            client = new GameClient(ip, port);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        System.out.println("Hello welcome to game");
        System.out.println("Please login");
        System.out.print("Username:");
        String user = input.next();
        System.out.print("Password:");
        String password;
        if (System.console() != null){ //Does not work in IDE
            password = System.console().readPassword().toString();
        } else{
            password = input.next();
        }
        session = client.getClient().login(user,password);
        if (session != null){
            System.out.println("Logged in!");
        } else{
            System.out.println("Login failed");
        }

        while (isRunning){
            switch (input.next()){
                case "say":
                    if (session == null){
                        System.out.println("Please log in");
                        break;
                    }

                    try {
                        client.getClient().sendMessage(session.getCharacter().getName() + ": " + input.nextLine());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case "logout":
                    try {
                        session.logout();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    session = null;
                    isRunning = false;
                    input.close();
                    System.out.println("logged out");
                    break;
                case "info":
                    if (session == null){
                        System.out.println("Please login.");
                        break;
                    }

                    try {
                        System.out.println("--- Session Info ---");
                        System.out.println("Name:" + session.getCharacter().getName());
                        System.out.println("---      End     ---");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Unknown Command.");
                    break;
            }
        }
        System.exit(0);
    }
}
